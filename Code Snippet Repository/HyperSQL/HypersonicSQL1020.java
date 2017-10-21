    public Table(Table table, HsqlName name) {

        persistenceScope    = SCOPE_STATEMENT;
        name.schema         = SqlInvariants.SYSTEM_SCHEMA_HSQLNAME;
        this.tableName      = name;
        this.database       = table.database;
        this.tableType      = RESULT_TABLE;
        this.columnList     = table.columnList;
        this.columnCount    = table.columnCount;
        this.indexList      = Index.emptyArray;
        this.constraintList = Constraint.emptyArray;

        createPrimaryKey();
    }
