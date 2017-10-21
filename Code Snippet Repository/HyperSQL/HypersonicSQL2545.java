    public void compileStatements() {

        writeLock.lock();

        try {
            getLob            = sysLobSession.compileStatement(getLobSQL);
            getSpanningBlocks = sysLobSession.compileStatement(getLobPartSQL);
            createLob         = sysLobSession.compileStatement(createLobSQL);
            createLobPartCall =
                sysLobSession.compileStatement(createLobPartCallSQL);
            createSingleLobPartCall =
                sysLobSession.compileStatement(createSingleLobPartCallSQL);
            divideLobPartCall =
                sysLobSession.compileStatement(divideLobPartCallSQL);
            deleteLobCall = sysLobSession.compileStatement(deleteLobCallSQL);
            deleteLobPartCall =
                sysLobSession.compileStatement(deleteLobPartCallSQL);
            updateLobLength =
                sysLobSession.compileStatement(updateLobLengthSQL);
            updateLobUsage = sysLobSession.compileStatement(updateLobUsageSQL);
            getNextLobId   = sysLobSession.compileStatement(getNextLobIdSQL);
            deleteUnusedLobs =
                sysLobSession.compileStatement(deleteUnusedCallSQL);
            mergeUnusedSpace =
                sysLobSession.compileStatement(mergeUnusedSpaceSQL);
            getLobUseLimit = sysLobSession.compileStatement(getLobUseLimitSQL);
            getLobCount    = sysLobSession.compileStatement(getLobCountSQL);

            //
            getSpanningParts = sysLobSession.compileStatement(getPartsSQL);
            getLastPart      = sysLobSession.compileStatement(getLastPartSQL);
            createPart       = sysLobSession.compileStatement(createPartSQL);
        } finally {
            writeLock.unlock();
        }
    }
