    public BlobDataID createBlob(long length) {

        long lobID = database.lobManager.createBlob(this, length);

        if (lobID == 0) {
            throw Error.error(ErrorCode.X_0F502);
        }

        sessionData.registerNewLob(lobID);

        return new BlobDataID(lobID);
    }
