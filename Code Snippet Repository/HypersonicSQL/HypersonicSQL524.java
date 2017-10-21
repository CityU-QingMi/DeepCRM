    public TableBase getResultTable() {

        if (resultTable != null) {
            return resultTable;
        }

        if (leftQueryExpression != null) {
            return leftQueryExpression.getResultTable();
        }

        return null;
    }
