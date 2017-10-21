    Expression XreadAndResolveBooleanValueExpression(RangeGroup[] rangeGroups,
            RangeGroup rangeGroup) {

        Expression condition = XreadBooleanValueExpression();
        HsqlList unresolved = condition.resolveColumnReferences(session,
            rangeGroup, rangeGroups, null);

        ExpressionColumn.checkColumnsResolved(unresolved);
        condition.resolveTypes(session, null);

        if (condition.isUnresolvedParam()) {
            condition.dataType = Type.SQL_BOOLEAN;
        }

        if (condition.getDataType() != Type.SQL_BOOLEAN) {
            throw Error.error(ErrorCode.X_42568);
        }

        return condition;
    }
