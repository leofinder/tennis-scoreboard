package com.craftelix.controller;

import com.craftelix.dto.FilterRequestDto;
import com.craftelix.dto.MatchFilterResponseDto;
import com.craftelix.service.FinishedMatchesPersistenceService;
import com.craftelix.util.JspHelper;
import com.craftelix.util.ValidationUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/matches")
public class MatchesController extends HttpServlet {

    private final FinishedMatchesPersistenceService finishedMatchesPersistenceService = FinishedMatchesPersistenceService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("filter_by_player_name");
        String page = req.getParameter("page");

        ValidationUtil.validatePage(page);

        FilterRequestDto filter = new FilterRequestDto(name == null ? "" : name.strip());
        int pageNumber = page == null || page.isBlank() ? 1 : Integer.parseInt(page);

        MatchFilterResponseDto matchFilterResponseDto = finishedMatchesPersistenceService.findByFilter(filter, pageNumber);

        req.setAttribute("page", matchFilterResponseDto.getPage());
        req.setAttribute("pageCount", matchFilterResponseDto.getPageCount());
        req.setAttribute("playerName", matchFilterResponseDto.getPlayerName());
        req.setAttribute("matches", matchFilterResponseDto.getMatches());
        req.getRequestDispatcher(JspHelper.getPath("matches")).forward(req, resp);
    }
}
