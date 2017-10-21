    public void setReturnTable(TableDerived table) {

        this.returnTable  = table;
        this.returnsTable = true;

        SimpleName[] names = new SimpleName[table.getColumnCount()];
        Type[]       types = table.getColumnTypes();

        returnType = new RowType(types);
    }
