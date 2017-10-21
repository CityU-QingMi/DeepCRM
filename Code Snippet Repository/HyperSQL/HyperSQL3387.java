    TableDerived XreadViewSubqueryTable(View view, boolean resolve) {

        compileContext.incrementDepth();

        QueryExpression queryExpression;

        try {
            queryExpression = XreadQueryExpression();
        } catch (HsqlException e) {
            queryExpression = XreadJoinedTableAsView();
        }

        queryExpression.setView(view);
        queryExpression.resolveReferences(session, RangeGroup.emptyArray);
        queryExpression.resolveTypesPartOne(session);
        queryExpression.resolveTypesPartTwo(session);

        if (resolve) {
            queryExpression.resolveTypesPartThree(session);
        }

        TableDerived td = new TableDerived(database, view.getName(),
                                           TableBase.VIEW_TABLE,
                                           queryExpression, null,
                                           OpTypes.NONE,
                                           compileContext.getDepth());

        td.view        = view;
        td.columnList  = view.columnList;
        td.columnCount = view.columnList.size();

        td.createPrimaryKey();

        td.triggerList  = view.triggerList;
        td.triggerLists = view.triggerLists;

        compileContext.decrementDepth();

        return td;
    }
