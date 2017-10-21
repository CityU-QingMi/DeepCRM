    private Expression readConcatExpressionOrNull() {

        Expression root;
        Expression r;

        // turn into a concatenation
        int position = getPosition();

        read();

        if (!readIfThis(Tokens.OPENBRACKET)) {
            rewind(position);

            return null;
        }

        root = XreadValueExpression();

        readThis(Tokens.COMMA);

        do {
            r    = XreadValueExpression();
            root = new ExpressionArithmetic(OpTypes.CONCAT, root, r);

            if (token.tokenType == Tokens.COMMA) {
                readThis(Tokens.COMMA);
            } else if (token.tokenType == Tokens.CLOSEBRACKET) {
                readThis(Tokens.CLOSEBRACKET);

                break;
            }
        } while (true);

        return root;
    }
