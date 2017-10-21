    public RowAction(Session session, TableBase table, byte type, Row row,
                     int[] colMap) {

        this.session         = session;
        this.type            = type;
        this.actionTimestamp = session.actionTimestamp;
        this.table           = table;
        this.store           = table.getRowStore(session);
        this.isMemory        = row.isMemory();
        this.memoryRow       = row;
        this.rowId           = row.getPos();
        this.changeColumnMap = colMap;
    }
