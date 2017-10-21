    public static ResultLob newLobTruncateRequest(long id, long offset) {

        ResultLob result = new ResultLob();

        result.subType     = LobResultTypes.REQUEST_TRUNCATE;
        result.lobID       = id;
        result.blockOffset = offset;

        return result;
    }
