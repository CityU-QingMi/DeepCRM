    private void processScript() {

        ScriptReaderBase scr = null;

        try {
            Crypto crypto = database.logger.getCrypto();

            if (crypto == null) {
                boolean compressed = database.logger.propScriptFormat == 3;

                scr = new ScriptReaderText(database, scriptFileName,
                                           compressed);
            } else {
                scr = new ScriptReaderDecode(database, scriptFileName, crypto,
                                             false);
            }

            Session session =
                database.sessionManager.getSysSessionForScript(database);

            scr.readAll(session);
            scr.close();
        } catch (Throwable e) {
            if (scr != null) {
                scr.close();

                if (cache != null) {
                    cache.release();
                }

                database.logger.textTableManager.closeAllTextCaches(false);
            }

            database.logger.logWarningEvent("Script processing failure", e);

            if (e instanceof HsqlException) {
                throw (HsqlException) e;
            } else if (e instanceof IOException) {
                throw Error.error(ErrorCode.FILE_IO_ERROR, e);
            } else if (e instanceof OutOfMemoryError) {
                throw Error.error(ErrorCode.OUT_OF_MEMORY);
            } else {
                throw Error.error(ErrorCode.GENERAL_ERROR, e);
            }
        }
    }
