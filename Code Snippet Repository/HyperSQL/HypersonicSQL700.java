    private RowAction(RowAction other) {

        this.session         = other.session;
        this.type            = other.type;
        this.actionTimestamp = other.actionTimestamp;
        this.table           = other.table;
        this.store           = other.store;
        this.isMemory        = other.isMemory;
        this.memoryRow       = other.memoryRow;
        this.rowId           = other.rowId;
        this.changeColumnMap = other.changeColumnMap;
    }
