    private void finaliseColumns() {

        indexLimitRowId   = indexLimitVisible;
        indexStartHaving  = indexLimitRowId + groupByColumnCount;
        indexStartOrderBy = indexStartHaving + havingColumnCount;
        indexStartAggregates = indexStartOrderBy
                               + sortAndSlice.getOrderLength();
        indexLimitData = indexLimitExpressions = indexStartAggregates;
        exprColumns    = new Expression[indexLimitExpressions];

        exprColumnList.toArray(exprColumns);

        exprColumnList = null;

        for (int i = 0; i < indexLimitVisible; i++) {
            exprColumns[i].queryTableColumnIndex = i;
        }

        if (sortAndSlice.hasOrder()) {
            for (int i = 0; i < sortAndSlice.getOrderLength(); i++) {
                exprColumns[indexStartOrderBy + i] =
                    (Expression) sortAndSlice.exprList.get(i);
            }
        }

        rowExpression = new Expression(OpTypes.ROW, exprColumns);
    }
