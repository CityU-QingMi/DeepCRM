    static QuerySpecification getCheckSelect(Session session, Table t,
            Expression e) {

        CompileContext compileContext = new CompileContext(session);

        compileContext.setNextRangeVarIndex(0);

        QuerySpecification s = new QuerySpecification(compileContext);
        RangeVariable range = new RangeVariable(t, null, null, null,
            compileContext);
        RangeVariable[] ranges     = new RangeVariable[]{ range };
        RangeGroup      rangeGroup = new RangeGroupSimple(ranges, false);

        e.resolveCheckOrGenExpression(session, rangeGroup, true);

        if (Type.SQL_BOOLEAN != e.getDataType()) {
            throw Error.error(ErrorCode.X_42568);
        }

        Expression condition = new ExpressionLogical(OpTypes.NOT, e);

        s.addSelectColumnExpression(EXPR_TRUE);
        s.addRangeVariable(session, range);
        s.addQueryCondition(condition);
        s.resolve(session);

        return s;
    }
