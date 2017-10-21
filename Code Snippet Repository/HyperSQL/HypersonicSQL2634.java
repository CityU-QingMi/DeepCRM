    public void setDataFileScale(int value) {

        if (propDataFileScale == value) {
            return;
        }

        checkPower(value, 10);

        if (value < 8 && value != 1) {
            throw Error.error(ErrorCode.X_42556);
        }

        if (hasCache()) {
            throw Error.error(ErrorCode.DATA_FILE_IN_USE);
        }

        propDataFileScale = value;
    }
