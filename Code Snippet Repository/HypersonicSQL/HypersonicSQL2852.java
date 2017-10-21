    public static ResultLob newLobCreateClobRequest(long sessionID,
            long lobID, Reader reader, long length) {

        ResultLob result = new ResultLob();

        result.lobID       = lobID;
        result.subType     = LobResultTypes.REQUEST_CREATE_CHARS;
        result.blockLength = length;
        result.reader      = reader;

        return result;
    }
