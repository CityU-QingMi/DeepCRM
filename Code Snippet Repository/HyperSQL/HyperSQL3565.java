    public void addTableColumns(RangeVariable subRange, Expression expression,
                                HashSet exclude) {

        if (subRange == this) {
            Table table = getTable();
            int   count = table.getColumnCount();

            addTableColumns(expression, 0, count, exclude);
        }
    }
