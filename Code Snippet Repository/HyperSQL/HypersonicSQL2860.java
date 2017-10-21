    public static ResultLob newLobGetBytesResponse(long id, long offset,
            byte[] block) {

        ResultLob result = new ResultLob();

        result.subType     = LobResultTypes.RESPONSE_GET_BYTES;
        result.lobID       = id;
        result.blockOffset = offset;
        result.byteBlock   = block;
        result.blockLength = block.length;

        return result;
    }
