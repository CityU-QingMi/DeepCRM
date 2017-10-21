    TableDerived XreadSubqueryTableBody(HsqlName name, int type) {

        int position = getPosition();

        compileContext.incrementDepth();

        QueryExpression queryExpression = XreadQueryExpression();
        TableDerived    td              = null;

        if (queryExpression.isValueList) {
            td = ((QuerySpecification) queryExpression).getValueListTable();
        }

        if (td == null) {
            td = newSubQueryTable(name, queryExpression, type);
        }

        td.setSQL(getLastPart(position));
        compileContext.decrementDepth();

        return td;
    }
