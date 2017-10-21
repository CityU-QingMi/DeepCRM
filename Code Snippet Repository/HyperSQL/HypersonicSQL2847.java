    public static ResultLob newLobGetBytesRequest(long id, long offset,
            int length) {

        ResultLob result = new ResultLob();

        result.subType     = LobResultTypes.REQUEST_GET_BYTES;
        result.lobID       = id;
        result.blockOffset = offset;
        result.blockLength = length;

        return result;
    }
