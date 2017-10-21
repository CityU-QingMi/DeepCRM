    private Expression readAggregateFunctionOrNull() {

        int        position = getPosition();
        int        tokenT   = token.tokenType;
        Expression expr;

        read();

        if (token.tokenType != Tokens.OPENBRACKET) {
            rewind(position);

            return null;
        }

        readThis(Tokens.OPENBRACKET);

        expr = readAggregateExpression(tokenT);

        readThis(Tokens.CLOSEBRACKET);
        readFilterClause(expr);

        return expr;
    }
