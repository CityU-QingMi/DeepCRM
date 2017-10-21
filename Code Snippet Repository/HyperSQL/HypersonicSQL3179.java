    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                      throws IOException, ServletException {

        String query = request.getQueryString();

        if ((query == null) || (query.length() == 0)) {
            response.setContentType("text/html");

            // fredt@users 20020130 - patch 1.7.0 by fredt
            // to avoid caching on the browser
            response.setHeader("Pragma", "no-cache");

            PrintWriter out = response.getWriter();

            out.println(
                "<html><head><title>HSQL Database Engine Servlet</title>");
            out.println("</head><body><h1>HSQL Database Engine Servlet</h1>");
            out.println("The servlet is running.<p>");

            if (initError == null) {
                out.println("Connected to the database.<p>");
                out.println("Database name: " + dbType + dbPath + "<p>");
            } else {
                out.println("<h2>The database is not available.</h2>");
                out.println("The error message is:<p>");
                out.println(initError);
            }

            out.println("</body></html>");
        }
    }
