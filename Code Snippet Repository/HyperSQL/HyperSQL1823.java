    public void setNioMaxSize(int value) {

        if (value < 8) {
            throw Error.error(ErrorCode.X_42556);
        }

        if (!ArrayUtil.isTwoPower(value, 10)) {
            if (value < 1024 || value % 512 != 0) {
                throw Error.error(ErrorCode.X_42556);
            }
        }

        propNioMaxSize = value * 1024L * 1024L;
    }
