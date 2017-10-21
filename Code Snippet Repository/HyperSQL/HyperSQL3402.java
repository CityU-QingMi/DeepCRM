    private Expression readCastExpressionOrNull() {

        Expression e;
        Type       typeObject;
        int        position = getPosition();

        read();
        readThis(Tokens.OPENBRACKET);

        e = XreadValueExpressionOrNull();

        readThis(Tokens.AS);

        typeObject = readTypeDefinition(false, true);

        if (e.isUnresolvedParam()) {
            e.setDataType(session, typeObject);
        } else {
            e = new ExpressionOp(e, typeObject);
        }

        readThis(Tokens.CLOSEBRACKET);

        return e;
    }
