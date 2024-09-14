package com.craftelix.controller;

import com.craftelix.dto.PlayerRequestDto;
import com.craftelix.service.OngoingMatchesService;
import com.craftelix.util.JspHelper;
import com.craftelix.util.ValidationUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/new-match")
public class NewMatchController extends HttpServlet {

    private final OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("new_match")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String playerOne = req.getParameter("player-one");
        String playerTwo = req.getParameter("player-two");

        PlayerRequestDto playerOneRequestDto = new PlayerRequestDto(playerOne);
        PlayerRequestDto playerTwoRequestDto = new PlayerRequestDto(playerTwo);

        ValidationUtil.validatePlayers(playerOneRequestDto, playerTwoRequestDto);

        UUID uuid = ongoingMatchesService.createMatch(playerOneRequestDto, playerTwoRequestDto);

        resp.sendRedirect("/match-score?uuid=" + uuid.toString());
    }
}
