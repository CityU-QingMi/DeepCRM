    private void getEventLogger() {

        if (fwLogger != null) {
            return;
        }

        String name = database.getNameString();

        if (name.isEmpty()) {

            // The database unique name is set up at different times
            // depending on upgraded / exiting / new databases.
            // Therefore FrameworkLogger is not used until the unique
            // name is known.
            return;
        }

        fwLogger = FrameworkLogger.getLog(SimpleLog.logTypeNameEngine,
                                          "hsqldb.db."
                                          + database.getNameString());
/**/
/**/
/**/
/**/
/**/
    }
