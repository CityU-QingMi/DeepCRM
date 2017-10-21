    private Expression readCoalesceExpression() {

        Expression c = null;

        read();
        readThis(Tokens.OPENBRACKET);

        Expression leaf = null;

        while (true) {
            Expression current = XreadValueExpression();

            if (leaf != null && token.tokenType == Tokens.CLOSEBRACKET) {
                readThis(Tokens.CLOSEBRACKET);
                leaf.setLeftNode(current);

                break;
            }

            Expression expressionNull = new ExpressionValue((Object) null,
                (Type) null);
            Expression condition = new ExpressionLogical(OpTypes.IS_NULL,
                current);
            Expression alt = new ExpressionOp(OpTypes.ALTERNATIVE,
                                              expressionNull, current);
            Expression casewhen = new ExpressionOp(OpTypes.CASEWHEN,
                                                   condition, alt);

            if (session.database.sqlSyntaxMys) {
                alt.setSubType(OpTypes.CAST);
                casewhen.setSubType(OpTypes.CAST);
            }

            if (c == null) {
                c = casewhen;
            } else {
                leaf.setLeftNode(casewhen);
            }

            leaf = alt;

            readThis(Tokens.COMMA);
        }

        return c;
    }
