    private Result setDatabase(Result resultIn) {

        try {
            String databaseName = resultIn.getDatabaseName();

            dbIndex = server.getDBIndex(databaseName);
            dbID    = server.dbID[dbIndex];
            user    = resultIn.getMainString();

            if (!server.isSilent()) {
                server.printWithThread(mThread + ":Trying to connect user '"
                                       + user + "' to DB (" + databaseName
                                       + ')');
            }

            session = DatabaseManager.newSession(dbID, user,
                                                 resultIn.getSubString(),
                                                 resultIn.getZoneString(),
                                                 resultIn.getUpdateCount());

            if (!server.isSilent()) {
                server.printWithThread(mThread + ":Connected user '" + user
                                       + "'");
            }

            return Result.newConnectionAcknowledgeResponse(session);
        } catch (HsqlException e) {
            session = null;

            return Result.newErrorResult(e);
        } catch (RuntimeException e) {
            session = null;

            return Result.newErrorResult(e);
        }
    }
