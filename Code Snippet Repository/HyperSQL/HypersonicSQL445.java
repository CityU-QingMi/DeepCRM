    private Expression readConvertExpressionOrNull() {

        Expression e;
        Expression mode = null;
        Type       typeObject;
        int        position = getPosition();

        read();

        if (!readIfThis(Tokens.OPENBRACKET)) {
            rewind(position);

            return null;
        }

        if (database.sqlSyntaxMss) {
            typeObject = readTypeDefinition(false, true);

            readThis(Tokens.COMMA);

            e = XreadValueExpressionOrNull();

            if (readIfThis(Tokens.COMMA)) {
                mode = this.XreadSimpleValueSpecificationOrNull();
            }
        } else {
            e = XreadValueExpressionOrNull();

            readThis(Tokens.COMMA);

            typeObject = Type.getTypeForJDBCConvertToken(token.tokenType);

            if (typeObject == null) {
                typeObject = readTypeDefinition(false, true);
            } else {
                read();
            }
        }

        if (e.isUnresolvedParam() && mode == null) {
            e.setDataType(session, typeObject);
        } else {
            e = new ExpressionOp(e, typeObject, mode);
        }

        readThis(Tokens.CLOSEBRACKET);

        return e;
    }
