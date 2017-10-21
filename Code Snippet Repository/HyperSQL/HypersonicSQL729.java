    public synchronized Number convertToNumber(String s,
            NumberType numberType) {

        Number  number;
        boolean minus = false;
        Type    type;

        reset(s);
        resetState();
        scanWhitespace();
        scanToken();
        scanWhitespace();

        if (token.tokenType == Tokens.PLUS_OP) {
            scanToken();
            scanWhitespace();
        } else if (token.tokenType == Tokens.MINUS_OP) {
            minus = true;

            scanToken();
            scanWhitespace();
        }

        if (!hasNonSpaceSeparator && token.tokenType == Tokens.X_VALUE
                && token.tokenValue instanceof Number) {
            number = (Number) token.tokenValue;
            type   = token.dataType;

            if (minus) {
                number = (Number) token.dataType.negate(number);
            }

            scanEnd();

            if (token.tokenType == Tokens.X_ENDPARSE) {
                number = (Number) numberType.convertToType(null, number, type);

                return number;
            }
        }

        throw Error.error(ErrorCode.X_22018);
    }
