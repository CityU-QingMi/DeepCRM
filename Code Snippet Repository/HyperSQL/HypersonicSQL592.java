    protected void addTableColumns(Expression expression, int start,
                                   int count, HashSet exclude) {

        Table         table = getTable();
        HsqlArrayList list  = new HsqlArrayList();

        for (int i = start; i < start + count; i++) {
            ColumnSchema column = table.getColumn(i);
            String columnName = columnAliases == null ? column.getName().name
                                                      : (String) columnAliases
                                                          .get(i);

            if (exclude != null && exclude.contains(columnName)) {
                continue;
            }

            Expression e = new ExpressionColumn(this, i);

            list.add(e);
        }

        Expression[] nodes = new Expression[list.size()];

        list.toArray(nodes);

        expression.nodes = nodes;
    }
