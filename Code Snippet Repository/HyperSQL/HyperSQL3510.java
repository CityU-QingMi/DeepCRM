    private void addAllJoinedColumns(Expression e) {

        HsqlArrayList list = new HsqlArrayList();

        for (int i = 0; i < rangeVariables.length; i++) {
            rangeVariables[i].addTableColumns(list);
        }

        Expression[] nodes = new Expression[list.size()];

        list.toArray(nodes);

        e.nodes = nodes;
    }
