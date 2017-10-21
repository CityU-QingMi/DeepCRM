    QueryExpression XreadQueryExpressionBody() {

        QueryExpression queryExpression = XreadQueryTerm();

        while (true) {
            switch (token.tokenType) {

                case Tokens.UNION :
                case Tokens.EXCEPT :
                case Tokens.MINUS_EXCEPT : {
                    queryExpression = XreadSetOperation(queryExpression);

                    break;
                }
                default : {
                    return queryExpression;
                }
            }
        }
    }
