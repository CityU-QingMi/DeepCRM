    QuerySpecification XreadQuerySpecification() {

        QuerySpecification select = XreadSelect();

        if (!select.isValueList
                && select.getCurrentRangeVariableCount() == 0) {
            XreadTableExpression(select);
        }

        return select;
    }
