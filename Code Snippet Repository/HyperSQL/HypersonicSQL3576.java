    public BlobData getBlob(SessionInterface session, long pos, long length) {

        ResultLob resultOut = ResultLob.newLobGetRequest(id, pos, length);
        Result    resultIn  = session.execute(resultOut);

        if (resultIn.isError()) {
            throw Error.error(resultIn);
        }

        long lobID = ((ResultLob) resultIn).getLobID();

        return new BlobDataID(lobID);
    }
