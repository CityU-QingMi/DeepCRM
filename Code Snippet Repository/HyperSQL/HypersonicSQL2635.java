    public void setDataFileScaleNoCheck(int value) {

        checkPower(value, 10);

        if (value < 8 && value != 1) {
            throw Error.error(ErrorCode.X_42556);
        }

        propDataFileScale = value;
    }
