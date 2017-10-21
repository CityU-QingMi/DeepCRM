    QueryExpression XreadQueryPrimary() {

        switch (token.tokenType) {

            case Tokens.TABLE :
            case Tokens.VALUES :
            case Tokens.SELECT : {
                QuerySpecification select = XreadSimpleTable();

                return select;
            }
            case Tokens.OPENBRACKET : {
                read();

                QueryExpression queryExpression = XreadQueryExpressionBody();
                SortAndSlice    sortAndSlice    = XreadOrderByExpression();

                readThis(Tokens.CLOSEBRACKET);

                if (queryExpression.sortAndSlice == null) {
                    queryExpression.addSortAndSlice(sortAndSlice);
                } else {
                    if (queryExpression.sortAndSlice.hasLimit()) {
                        if (sortAndSlice.hasLimit()) {
                            throw Error.error(ErrorCode.X_42549);
                        }

                        for (int i = 0; i < sortAndSlice.exprList.size();
                                i++) {
                            Expression e =
                                (Expression) sortAndSlice.exprList.get(i);

                            queryExpression.sortAndSlice.addOrderExpression(e);
                        }
                    } else {
                        queryExpression.addSortAndSlice(sortAndSlice);
                    }
                }

                return queryExpression;
            }
            default : {
                throw unexpectedToken();
            }
        }
    }
