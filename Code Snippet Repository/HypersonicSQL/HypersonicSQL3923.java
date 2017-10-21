    void set(TransferDb database, Traceable t, String q) {

        super.set(database, t, q);

        // set the Dateformat for our connection
        String dateFormatStmnt =
            "ALTER SESSION SET NLS_DATE_FORMAT = 'YYYY-MM-DD HH24:MI:SS'";

        System.out.println("dateFormatStmnt: " + dateFormatStmnt);

        try {
            tracer.trace("Executing " + dateFormatStmnt);
            database.execute(dateFormatStmnt);
        } catch (Exception e) {
            tracer.trace("Ignoring error " + e.getMessage());
            System.out.println("Ignoring error " + e.getMessage());
        }
    }
