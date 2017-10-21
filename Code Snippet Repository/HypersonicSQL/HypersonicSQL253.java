    public HsqlName newColumnHsqlName(HsqlName table, String name,
                                      boolean isquoted) {

        HsqlName hsqlName = new HsqlName(this, name, isquoted,
                                         SchemaObject.COLUMN);

        hsqlName.schema = table.schema;
        hsqlName.parent = table;

        return hsqlName;
    }
