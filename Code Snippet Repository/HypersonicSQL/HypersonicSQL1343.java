    protected final void addColumn(Table t, String name, Type type) {

        HsqlName     cn;
        ColumnSchema c;

        cn = HsqlNameManager.newInfoSchemaColumnName(name, t.getName());
        c  = new ColumnSchema(cn, type, true, false, null);

        t.addColumn(c);
    }
