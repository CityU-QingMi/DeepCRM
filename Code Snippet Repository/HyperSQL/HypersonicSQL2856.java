    public static ResultLob newLobGetCharsRequest(long id, long offset,
            int length) {

        ResultLob result = new ResultLob();

        result.subType     = LobResultTypes.REQUEST_GET_CHARS;
        result.lobID       = id;
        result.blockOffset = offset;
        result.blockLength = length;

        return result;
    }
