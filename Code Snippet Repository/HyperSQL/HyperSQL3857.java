    public ClobDataID createClob(long length) {

        long lobID = database.lobManager.createClob(this, length);

        if (lobID == 0) {
            throw Error.error(ErrorCode.X_0F502);
        }

        sessionData.registerNewLob(lobID);

        return new ClobDataID(lobID);
    }
