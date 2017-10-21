    long readBigint() {

        boolean minus = false;

        if (token.tokenType == Tokens.MINUS_OP) {
            minus = true;

            read();
        }

        checkIsValue();

        if (minus && token.dataType.typeCode == Types.SQL_NUMERIC
                && LONG_MAX_VALUE_INCREMENT.equals(token.tokenValue)) {
            read();

            return Long.MIN_VALUE;
        }

        if (token.dataType.typeCode != Types.SQL_INTEGER
                && token.dataType.typeCode != Types.SQL_BIGINT) {
            throw Error.error(ErrorCode.X_42563);
        }

        long val = ((Number) token.tokenValue).longValue();

        if (minus) {
            val = -val;
        }

        read();

        return val;
    }
