    static void checkColumnsResolved(HsqlList set) {

        if (set != null && !set.isEmpty()) {
            Expression e = (Expression) set.get(0);

            if (e instanceof ExpressionColumn) {
                StringBuffer     sb = new StringBuffer();
                ExpressionColumn c  = (ExpressionColumn) e;

                if (c.schema != null) {
                    sb.append(c.schema + '.');
                }

                if (c.tableName != null) {
                    sb.append(c.tableName + '.');
                }

                sb.append(c.getColumnName());

                throw Error.error(ErrorCode.X_42501, sb.toString());
            } else {
                OrderedHashSet newSet = new OrderedHashSet();

                e.collectAllExpressions(newSet,
                                        Expression.columnExpressionSet,
                                        Expression.emptyExpressionSet);

                // throw with column name
                checkColumnsResolved(newSet);

                // throw anyway if not found
                throw Error.error(ErrorCode.X_42501);
            }
        }
    }
