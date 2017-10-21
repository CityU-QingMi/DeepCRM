    void performPostExecute() throws SQLException {

        resultOut.clearLobResults();

        generatedResult = null;

        if (resultIn == null) {
            return;
        }

        rootWarning = null;

        Result current = resultIn;

        while (current.getChainedResult() != null) {
            current = current.getUnlinkChainedResult();

            if (current.getType() == ResultConstants.WARNING) {
                SQLWarning w = JDBCUtil.sqlWarning(current);

                if (rootWarning == null) {
                    rootWarning = w;
                } else {
                    rootWarning.setNextWarning(w);
                }
            } else if (current.getType() == ResultConstants.ERROR) {
                errorResult = current;
            } else if (current.getType() == ResultConstants.GENERATED) {
                generatedResult = current;
            } else if (current.getType() == ResultConstants.DATA) {
                resultIn.addChainedResult(current);
            }
        }

        if (rootWarning != null) {
            connection.setWarnings(rootWarning);
        }
    }
