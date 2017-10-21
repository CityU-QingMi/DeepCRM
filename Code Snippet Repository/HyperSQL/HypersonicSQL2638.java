    public void setLobFileCompressed(boolean value) {

        if (propCompressLobs == value) {
            return;
        }

        if (database.lobManager.getLobCount() > 0) {
            throw Error.error(ErrorCode.DATA_FILE_IN_USE);
        }

        propCompressLobs = value;

        database.lobManager.close();
        database.lobManager.open();
    }
