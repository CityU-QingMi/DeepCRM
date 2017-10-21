    public static ResultLob newLobGetCharsResponse(long id, long offset,
            char[] chars) {

        ResultLob result = new ResultLob();

        result.subType     = LobResultTypes.RESPONSE_GET_CHARS;
        result.lobID       = id;
        result.blockOffset = offset;
        result.charBlock   = chars;
        result.blockLength = chars.length;

        return result;
    }
