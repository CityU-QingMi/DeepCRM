    public static ResultLob newLobSetBytesRequest(long id, long offset,
            byte[] block) {

        ResultLob result = new ResultLob();

        result.subType     = LobResultTypes.REQUEST_SET_BYTES;
        result.lobID       = id;
        result.blockOffset = offset;
        result.byteBlock   = block;
        result.blockLength = block.length;

        return result;
    }
