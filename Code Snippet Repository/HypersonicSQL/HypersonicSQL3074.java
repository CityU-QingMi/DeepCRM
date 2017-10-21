    private void handleException(HsqlException e) {

        if (database.recoveryMode == 0) {
            throw e;
        }

        if (scrwriter == null) {
            String name = database.getPath() + ".reject";

            scrwriter = new ScriptWriterText(database, name, true, true, true);
        }

        try {
            scrwriter.writeLogStatement(null, rawStatement);
        } catch (Throwable t) {}
    }
