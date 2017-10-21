    int readInteger() {

        boolean minus = false;

        if (token.tokenType == Tokens.MINUS_OP) {
            minus = true;

            read();
        }

        checkIsValue();

        if (minus && token.dataType.typeCode == Types.SQL_BIGINT
                && ((Number) token.tokenValue).longValue()
                   == -(long) Integer.MIN_VALUE) {
            read();

            return Integer.MIN_VALUE;
        }

        if (token.dataType.typeCode != Types.SQL_INTEGER) {
            throw Error.error(ErrorCode.X_42563);
        }

        int val = ((Number) token.tokenValue).intValue();

        if (minus) {
            val = -val;
        }

        read();

        return val;
    }
