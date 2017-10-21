    public static ResultLob newLobCreateBlobRequest(long sessionID,
            long lobID, InputStream stream, long length) {

        ResultLob result = new ResultLob();

        result.lobID       = lobID;
        result.subType     = LobResultTypes.REQUEST_CREATE_BYTES;
        result.blockLength = length;
        result.stream      = stream;

        return result;
    }
