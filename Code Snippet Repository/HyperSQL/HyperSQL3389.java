    private TableDerived XreadRowValueExpressionList() {

        compileContext.incrementDepth();

        Expression   e  = XreadRowValueExpressionListBody();
        TableDerived td = prepareSubqueryTable(e, null);

        compileContext.decrementDepth();

        return td;
    }
