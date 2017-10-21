    private void readFilterClause(Expression e) {

        int position = getPosition();

        if (token.tokenType == Tokens.FILTER) {
            read();

            if (token.tokenType != Tokens.OPENBRACKET) {
                rewind(position);

                return;
            }

            readThis(Tokens.OPENBRACKET);
            readThis(Tokens.WHERE);

            Expression condition = XreadBooleanValueExpression();

            e.setCondition(condition);
            readThis(Tokens.CLOSEBRACKET);
        }
    }
