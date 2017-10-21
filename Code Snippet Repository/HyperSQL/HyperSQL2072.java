    public static ResultLob newLobGetBytePatternPositionRequest(long id,
            byte[] pattern, long offset) {

        ResultLob result = new ResultLob();

        result.subType     = LobResultTypes.REQUEST_GET_BYTE_PATTERN_POSITION;
        result.lobID       = id;
        result.blockOffset = offset;
        result.byteBlock   = pattern;
        result.blockLength = pattern.length;

        return result;
    }
