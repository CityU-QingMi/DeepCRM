    public static ResultLob newLobSetCharsRequest(long id, long offset,
            char[] chars) {

        ResultLob result = new ResultLob();

        result.subType     = LobResultTypes.REQUEST_SET_CHARS;
        result.lobID       = id;
        result.blockOffset = offset;
        result.charBlock   = chars;
        result.blockLength = chars.length;

        return result;
    }
