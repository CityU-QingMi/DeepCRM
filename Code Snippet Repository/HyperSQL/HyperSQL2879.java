    void XreadUnionCorrespondingClause(QueryExpression queryExpression) {

        if (token.tokenType == Tokens.CORRESPONDING) {
            read();
            queryExpression.setUnionCorresoponding();

            if (token.tokenType == Tokens.BY) {
                read();

                OrderedHashSet names = readColumnNames(false);

                queryExpression.setUnionCorrespondingColumns(names);
            }
        }
    }
