    private TableDerived prepareSubqueryTable(Expression e,
            HsqlName[] colNames) {

        HsqlList unresolved = e.resolveColumnReferences(session,
            RangeGroup.emptyGroup, compileContext.getOuterRanges(), null);

        ExpressionColumn.checkColumnsResolved(unresolved);
        e.resolveTypes(session, null);
        e.prepareTable(session, null, e.nodes[0].nodes.length);

        TableDerived td = newSubQueryTable(e, OpTypes.VALUELIST);

        td.prepareTable(session, colNames);

        return td;
    }
