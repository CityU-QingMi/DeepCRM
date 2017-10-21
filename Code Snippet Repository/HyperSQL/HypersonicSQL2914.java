    public static int getCheckSingleRight(String right) {

        int r = getRight(right);

        if (r != 0) {
            return r;
        }

        throw Error.error(ErrorCode.X_42581, right);
    }
