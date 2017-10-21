    Expression readCollection(int type) {

        read();

        if (token.tokenType == Tokens.OPENBRACKET) {
            return XreadArrayConstructor();
        } else {
            readThis(Tokens.LEFTBRACKET);

            HsqlArrayList list = new HsqlArrayList();

            for (int i = 0; ; i++) {
                if (token.tokenType == Tokens.RIGHTBRACKET) {
                    read();

                    break;
                }

                if (i > 0) {
                    readThis(Tokens.COMMA);
                }

                Expression e = XreadValueExpressionOrNull();

                list.add(e);
            }

            Expression[] array = new Expression[list.size()];

            list.toArray(array);

            return new Expression(OpTypes.ARRAY, array);
        }
    }
