    public void setLobFileScale(int value) {

        if (propLobBlockSize == value * 1024) {
            return;
        }

        checkPower(value, 5);

        if (database.lobManager.getLobCount() > 0) {
            throw Error.error(ErrorCode.DATA_FILE_IN_USE);
        }

        propLobBlockSize = value * 1024;

        database.lobManager.close();
        database.lobManager.open();
    }
