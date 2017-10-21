    public synchronized Result execute(Result r) {

        if (isClosed) {
            return Result.newErrorResult(Error.error(ErrorCode.X_08503));
        }

        try {
            r.setSessionId(sessionID);
            r.setDatabaseId(databaseID);
            write(r);

            return read();
        } catch (Throwable e) {
            throw Error.error(ErrorCode.X_08006, e.toString());
        }
    }
