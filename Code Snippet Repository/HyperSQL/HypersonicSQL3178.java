    public void init(ServletConfig config) {

        try {
            super.init(config);
        } catch (ServletException e) {
            log(e.toString());
        }

        String dbStr = getInitParameter("hsqldb.server.database");

        if (dbStr == null) {
            dbStr = ".";
        }

// begin WEB-INF patch */
        String useWebInfStr =
            getInitParameter("hsqldb.server.use_web-inf_path");

        if (!dbStr.equals(".") && "true".equalsIgnoreCase(useWebInfStr)) {
            dbStr = getServletContext().getRealPath("/") + "WEB-INF/" + dbStr;
        }

// end WEB-INF patch
        HsqlProperties dbURL = DatabaseURL.parseURL(dbStr, false, false);

        log("Database filename = " + dbStr);

        if (dbURL == null) {
            initError = "Bad Database name";
        } else {
            dbPath = dbURL.getProperty("database");
            dbType = dbURL.getProperty("connection_type");

            try {
                DatabaseManager.getDatabase(dbType, dbPath, dbURL);
            } catch (HsqlException e) {
                initError = e.getMessage();
            }
        }

        if (initError == null) {
            log("Initialization completed.");
        } else {
            log("Database could not be initialised.");
            log(initError);
        }
    }
