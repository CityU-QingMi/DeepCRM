    private QueryExpression XreadSetOperation(
            QueryExpression queryExpression) {

        queryExpression = new QueryExpression(compileContext, queryExpression);

        int unionType = XreadUnionType();

        XreadUnionCorrespondingClause(queryExpression);

        QueryExpression rightQueryExpression = XreadQueryTerm();

        queryExpression.addUnion(rightQueryExpression, unionType);

        return queryExpression;
    }
