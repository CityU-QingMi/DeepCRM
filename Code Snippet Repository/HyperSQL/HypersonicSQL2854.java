    public static ResultLob newLobGetRequest(long id, long offset,
            long length) {

        ResultLob result = new ResultLob();

        result.subType     = LobResultTypes.REQUEST_GET_LOB;
        result.lobID       = id;
        result.blockOffset = offset;
        result.blockLength = length;

        return result;
    }
