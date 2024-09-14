package com.craftelix.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JspHelper {

    private static final String JSP_FORMAT = "/jsp/%s.jsp";

    public static String getPath(String jspName) {
        return String.format(JSP_FORMAT, jspName);
    }
}
