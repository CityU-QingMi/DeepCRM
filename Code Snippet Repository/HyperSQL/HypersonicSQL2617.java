    void writeScript(boolean full) {

        deleteNewScript();

        ScriptWriterBase scw;
        Crypto           crypto = database.logger.getCrypto();

        if (crypto == null) {
            boolean compressed = database.logger.propScriptFormat == 3;

            scw = new ScriptWriterText(database,
                                       scriptFileName
                                       + Logger.newFileExtension, full,
                                           compressed);
        } else {
            scw = new ScriptWriterEncode(database,
                                         scriptFileName
                                         + Logger.newFileExtension, full,
                                             crypto);
        }

        scw.writeAll();
        scw.close();

        scw = null;
    }
