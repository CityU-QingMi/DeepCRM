    private Result cancelStatement(Result resultIn) {

        try {
            dbID = resultIn.getDatabaseId();

            long sessionId = resultIn.getSessionId();

            session = DatabaseManager.getSession(dbID, sessionId);

            if (!server.isSilent()) {
                server.printWithThread(mThread
                                       + ":Trying to cancel statement "
                                       + " to DB (" + dbID + ')');
            }

            return session.cancel(resultIn);
        } catch (HsqlException e) {
            session = null;

            return Result.updateZeroResult;
        } catch (Throwable t) {
            session = null;

            return Result.updateZeroResult;
        }
    }
