    public static void runScript(Database database, String logFilename,
                                 boolean fullReplay) {

        Crypto           crypto = database.logger.getCrypto();
        ScriptReaderBase scr;

        try {
            if (crypto == null) {
                scr = new ScriptReaderText(database, logFilename, false);
            } else {
                scr = new ScriptReaderDecode(database, logFilename, crypto,
                                             true);
            }
        } catch (Throwable e) {

            // catch out-of-memory errors and terminate
            if (e instanceof EOFException) {

                // end of file - normal end
            } else {

                // stop processing on bad script line
                database.logger.logSevereEvent("opening log file", e);
            }

            return;
        }

        runScript(database, scr, fullReplay);
    }
