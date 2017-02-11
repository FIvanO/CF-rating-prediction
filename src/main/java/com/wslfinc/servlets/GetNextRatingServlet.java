package com.wslfinc.servlets;

import com.wslfinc.cf.sdk.CodeForcesSDK;
import com.wslfinc.cf.sdk.entities.additional.ContestantResult;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author Wsl_F
 */
public class GetNextRatingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Request received!");
        System.out.println("Parameter: " + request.getParameter("contestId"));
        int contestId = Integer.valueOf(request.getParameter("contestId"));

        List<ContestantResult> nextRating = CodeForcesSDK.getNewRatings(contestId);
        JSONObject json = ContestantResult.toJSON(nextRating);
        request.setAttribute("nextRating", json);

        try {
            PrintWriter out = response.getWriter();
            out.write(json.toString());
            out.flush();
            response.setStatus(200);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

}