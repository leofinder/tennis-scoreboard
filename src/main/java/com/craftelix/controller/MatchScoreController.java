package com.craftelix.controller;

import com.craftelix.dto.FinishedMatchRequestDto;
import com.craftelix.dto.PlayerRequestDto;
import com.craftelix.entity.Match;
import com.craftelix.entity.MatchScore;
import com.craftelix.entity.Player;
import com.craftelix.entity.PlayerType;
import com.craftelix.exception.NotFoundException;
import com.craftelix.mapper.MatchMapper;
import com.craftelix.mapper.PlayerTypeMapper;
import com.craftelix.service.FinishedMatchesPersistenceService;
import com.craftelix.service.MatchScoreCalculationService;
import com.craftelix.service.OngoingMatchesService;
import com.craftelix.util.JspHelper;
import com.craftelix.util.ValidationUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreController extends HttpServlet {

    private final OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getInstance();
    private final MatchScoreCalculationService matchScoreCalculationService = MatchScoreCalculationService.getInstance();
    private final FinishedMatchesPersistenceService finishedMatchesPersistenceService = FinishedMatchesPersistenceService.getInstance();

    private final MatchMapper matchMapper = MatchMapper.INSTANCE;
    private final PlayerTypeMapper playerTypeMapper = PlayerTypeMapper.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String uuid = req.getParameter("uuid");

        ValidationUtil.validateUUID(uuid);

        Match match = getMatch(uuid);

        forwardToMatchScore(req, resp, uuid, match);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String uuid = req.getParameter("uuid");
        String playerTypeString = req.getParameter("player");

        ValidationUtil.validateUUID(uuid);
        ValidationUtil.validatePlayerType(playerTypeString);

        Match match = getMatch(uuid);
        PlayerType playerType = playerTypeMapper.toEntity(playerTypeString);
        MatchScore score = matchScoreCalculationService.updateMatchPoint(match.getScore(), playerType);

        if (matchScoreCalculationService.isMatchOver(score)) {
            FinishedMatchRequestDto finishedMatchRequestDto = buildFinishedMatchRequestDto(match, playerType);
            ongoingMatchesService.deleteMatch(UUID.fromString(uuid));
            finishedMatchesPersistenceService.save(finishedMatchRequestDto);
            forwardToMatchResult(req, resp, match, playerType);
        } else {
            forwardToMatchScore(req, resp, uuid, match);
        }
    }

    private FinishedMatchRequestDto buildFinishedMatchRequestDto(Match match, PlayerType playerType) {
        Player winner = playerType == PlayerType.PLAYER_ONE ? match.getPlayerOne() : match.getPlayerTwo();
        return new FinishedMatchRequestDto(
                new PlayerRequestDto(match.getPlayerOne().getName()),
                new PlayerRequestDto(match.getPlayerTwo().getName()),
                new PlayerRequestDto(winner.getName())
        );
    }

    private void forwardToMatchScore(HttpServletRequest req, HttpServletResponse resp, String uuid, Match match) throws ServletException, IOException {
        req.setAttribute("uuid", uuid);
        req.setAttribute("match", matchMapper.toDto(match));
        req.getRequestDispatcher(JspHelper.getPath("match_score")).forward(req, resp);
    }

    private void forwardToMatchResult(HttpServletRequest req, HttpServletResponse resp, Match match, PlayerType playerType) throws ServletException, IOException {
        match.setWinner(playerType == PlayerType.PLAYER_ONE ? match.getPlayerOne() : match.getPlayerTwo());
        req.setAttribute("match", matchMapper.toDto(match));
        req.getRequestDispatcher(JspHelper.getPath("match_result")).forward(req, resp);
    }

    private Match getMatch(String uuid) {

        Optional<Match> match = ongoingMatchesService.getMatch(UUID.fromString(uuid));
        if (match.isEmpty()) {
            throw new NotFoundException("Match not found");
        }
        return match.get();
    }
}
