    public static ResultLob newLobGetBytePatternPositionRequest(long id,
            long otherId, long offset) {

        ResultLob result = new ResultLob();

        result.subType     = LobResultTypes.REQUEST_GET_BYTE_PATTERN_POSITION;
        result.lobID       = id;
        result.blockOffset = offset;

        return result;
    }
