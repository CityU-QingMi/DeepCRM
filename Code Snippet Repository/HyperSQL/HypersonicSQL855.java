    Result performLOBOperation(ResultLob cmd) {

        long id        = cmd.getLobID();
        int  operation = cmd.getSubType();

        switch (operation) {

            case ResultLob.LobResultTypes.REQUEST_GET_LOB : {
                return database.lobManager.getLob(id, cmd.getOffset(),
                                                  cmd.getBlockLength());
            }
            case ResultLob.LobResultTypes.REQUEST_GET_LENGTH : {
                return database.lobManager.getLength(id);
            }
            case ResultLob.LobResultTypes.REQUEST_GET_BYTES : {
                return database.lobManager.getBytes(
                    id, cmd.getOffset(), (int) cmd.getBlockLength());
            }
            case ResultLob.LobResultTypes.REQUEST_SET_BYTES : {
                return database.lobManager.setBytes(
                    id, cmd.getOffset(), cmd.getByteArray(),
                    (int) cmd.getBlockLength());
            }
            case ResultLob.LobResultTypes.REQUEST_GET_CHARS : {
                return database.lobManager.getChars(
                    id, cmd.getOffset(), (int) cmd.getBlockLength());
            }
            case ResultLob.LobResultTypes.REQUEST_SET_CHARS : {
                return database.lobManager.setChars(
                    id, cmd.getOffset(), cmd.getCharArray(),
                    (int) cmd.getBlockLength());
            }
            case ResultLob.LobResultTypes.REQUEST_TRUNCATE : {
                return database.lobManager.truncate(id, cmd.getOffset());
            }
            case ResultLob.LobResultTypes.REQUEST_DUPLICATE_LOB : {
                return database.lobManager.createDuplicateLob(id);
            }
            case ResultLob.LobResultTypes.REQUEST_CREATE_BYTES :
            case ResultLob.LobResultTypes.REQUEST_CREATE_CHARS :
            case ResultLob.LobResultTypes.REQUEST_GET_BYTE_PATTERN_POSITION :
            case ResultLob.LobResultTypes.REQUEST_GET_CHAR_PATTERN_POSITION : {
                throw Error.error(ErrorCode.X_0A501);
            }
            default : {
                throw Error.runtimeError(ErrorCode.U_S0500, "Session");
            }
        }
    }
