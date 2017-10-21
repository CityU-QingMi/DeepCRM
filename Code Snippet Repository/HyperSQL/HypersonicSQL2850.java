    public static ResultLob newLobGetCharPatternPositionRequest(long id,
            long otherId, long offset) {

        ResultLob result = new ResultLob();

        result.subType     = LobResultTypes.REQUEST_GET_CHAR_PATTERN_POSITION;
        result.lobID       = id;
        result.blockOffset = offset;
        result.blockLength = otherId;

        return result;
    }
