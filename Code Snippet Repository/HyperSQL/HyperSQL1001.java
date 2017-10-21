    public synchronized void resetSession() {

        Result login    = Result.newResetSessionRequest();
        Result resultIn = execute(login);

        if (resultIn.isError()) {
            isClosed = true;

            closeConnection();

            throw Error.error(resultIn);
        }

        sessionID  = resultIn.getSessionId();
        databaseID = resultIn.getDatabaseId();
    }
