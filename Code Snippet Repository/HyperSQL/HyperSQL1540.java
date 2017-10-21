    public RowSetNavigatorDataTable(Session session, Table table) {

        super(session);

        this.table         = table;
        visibleColumnCount = table.getColumnCount();
        mainIndex          = table.getPrimaryIndex();
        fullIndex          = table.getFullIndex(session);
        store              = table.getRowStore(session);
        this.size          = (int) mainIndex.size(session, store);

        reset();
    }
