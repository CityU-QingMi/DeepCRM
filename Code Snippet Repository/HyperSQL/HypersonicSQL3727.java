    public static int getIntervalType(String part) {

        int index = ArrayUtil.find(Tokens.SQL_INTERVAL_FIELD_NAMES, part);

        if (index < 0) {
            throw Error.error(ErrorCode.X_42562);
        }

        return intervalParts[index];
    }
