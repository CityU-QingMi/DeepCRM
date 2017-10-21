    protected void sendRedirect(HttpServletResponse response, String finalLocation) throws IOException {
        if (SC_FOUND == statusCode) {
            response.sendRedirect(finalLocation);
        } else {
            response.setStatus(statusCode);
            response.setHeader("Location", finalLocation);
            response.getWriter().write(finalLocation);
            response.getWriter().close();
        }

    }
