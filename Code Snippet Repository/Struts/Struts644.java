    protected void sendErrorResponse(HttpServletRequest request, HttpServletResponse response, int code, Exception e) {
        try {
            // WW-1977: Only put errors in the request when code is a 500 error
            if (code == HttpServletResponse.SC_INTERNAL_SERVER_ERROR) {
                // WW-4103: Only logs error when application error occurred, not Struts error
                LOG.error("Exception occurred during processing request: {}", e.getMessage(), e);
                // send a http error response to use the servlet defined error handler
                // make the exception available to the web.xml defined error page
                request.setAttribute("javax.servlet.error.exception", e);

                // for compatibility
                request.setAttribute("javax.servlet.jsp.jspException", e);
            }

            // send the error response
            response.sendError(code, e.getMessage());
        } catch (IOException e1) {
            // we're already sending an error, not much else we can do if more stuff breaks
        }
    }
