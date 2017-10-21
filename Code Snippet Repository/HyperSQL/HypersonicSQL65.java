    void checkCheckConstraint(Session session, Table table) {

        if (table.getRowStore(session).elementCount() > 0) {
            Expression newCheck = getNewCheckExpression(session);
            QuerySpecification checkSelect = Expression.getCheckSelect(session,
                table, newCheck);
            Result r = checkSelect.getResult(session, 1);

            if (r.getNavigator().getSize() != 0) {
                String[] info = new String[] {
                    name.statementName, table.getName().statementName
                };

                throw Error.error(null, ErrorCode.X_23513,
                                  ErrorCode.CONSTRAINT, info);
            }
        }
    }
