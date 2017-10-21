    public static ResultLob newLobGetCharPatternPositionRequest(long id,
            char[] pattern, long offset) {

        ResultLob result = new ResultLob();

        result.subType     = LobResultTypes.REQUEST_GET_CHAR_PATTERN_POSITION;
        result.lobID       = id;
        result.blockOffset = offset;
        result.charBlock   = pattern;
        result.blockLength = pattern.length;

        return result;
    }
