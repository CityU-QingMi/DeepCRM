    Expression XreadSimpleValueSpecificationOrNull() {

        Expression e;

        switch (token.tokenType) {

            case Tokens.X_VALUE :
                e = new ExpressionValue(token.tokenValue, token.dataType);

                read();

                return e;

            case Tokens.COLON :
                read();

                if (token.tokenType == Tokens.X_DELIMITED_IDENTIFIER
                        || token.tokenType == Tokens.X_IDENTIFIER) {}
                else {
                    throw unexpectedToken(Tokens.T_COLON);
                }

            // fall through
            case Tokens.QUESTION :
                ExpressionColumn p =
                    new ExpressionColumn(OpTypes.DYNAMIC_PARAM);

                compileContext.addParameter(p, getPosition());
                read();

                return p;

            case Tokens.X_IDENTIFIER :
            case Tokens.X_DELIMITED_IDENTIFIER :
                checkValidCatalogName(token.namePrePrePrefix);

                e = new ExpressionColumn(token.namePrePrefix,
                                         token.namePrefix, token.tokenString);

                read();

                return e;

            default :
                return null;
        }
    }
