    public ColumnBase(String catalog, ColumnSchema other) {

        this.catalog      = catalog;
        this.schema       = other.getSchemaNameString();
        this.table        = other.getTableNameString();
        this.name         = other.getNameString();
        this.nullability  = other.getNullability();
        this.isIdentity   = other.isIdentity();
        this.isSearchable = other.isSearchable();
        this.isWriteable  = other.isWriteable();
    }
