    public static ResultLob newLobSetResponse(long id, long length) {

        ResultLob result = new ResultLob();

        result.subType     = LobResultTypes.RESPONSE_SET;
        result.lobID       = id;
        result.blockLength = length;

        return result;
    }
