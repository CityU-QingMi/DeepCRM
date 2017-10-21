    protected void printJson(HttpServletResponse response, String finalLocation) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        response.setHeader("Location", finalLocation);
        PrintWriter writer = response.getWriter();
        writer.write("{\"location\": \"");
        writer.write(finalLocation);
        writer.write("\"}");
        writer.close();
    }
