    public static ResultLob newLobTruncateResponse(long id, long length) {

        ResultLob result = new ResultLob();

        result.subType     = LobResultTypes.RESPONSE_TRUNCATE;
        result.lobID       = id;
        result.blockLength = length;

        return result;
    }
