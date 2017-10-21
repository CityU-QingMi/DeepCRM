    private Expression resolveColumnReferencesInGroupBy(Session session,
            Expression expression) {

        if (expression == null) {
            return null;
        }

        HsqlList list = expression.resolveColumnReferences(session, this,
            rangeVariables.length, RangeGroup.emptyArray, null, false);

        if (list != null) {

            // if not resolved, resolve as simple alias
            if (expression.getType() == OpTypes.COLUMN) {
                Expression resolved = expression.replaceAliasInOrderBy(session,
                    exprColumns, indexLimitVisible);

                if (resolved != expression) {
                    return resolved;
                }
            }

            // resolve and allocate to throw exception
            resolveColumnReferencesAndAllocate(session, expression,
                                               rangeVariables.length,
                                               RangeGroup.emptyArray, false);
        }

        return expression;
    }
