    private Expression readGreatestExpressionOrNull() {

        int position = getPosition();

        read();

        if (!readIfThis(Tokens.OPENBRACKET)) {
            rewind(position);

            return null;
        }

        Expression casewhen = null;

        do {
            casewhen = readValue(casewhen, OpTypes.GREATER);

            if (token.tokenType == Tokens.COMMA) {
                readThis(Tokens.COMMA);
            } else {
                break;
            }
        } while (true);

        readThis(Tokens.CLOSEBRACKET);

        return casewhen;
    }
