    public RowSetNavigatorDataTable(Session session,
                                    QuerySpecification select) {

        super(session);

        this.rangePosition = select.resultRangePosition;
        visibleColumnCount = select.indexLimitVisible;
        table              = select.resultTable.duplicate();
        store = session.sessionData.getNewResultRowStore(table,
                !select.isAggregated);
        table.store       = store;
        isAggregate       = select.isAggregated;
        isSimpleAggregate = select.isAggregated && !select.isGrouped;
        reindexTable      = select.isGrouped;
        mainIndex         = select.mainIndex;
        fullIndex         = select.fullIndex;
        orderIndex        = select.orderIndex;
        groupIndex        = select.groupIndex;
        idIndex           = select.idIndex;
        tempRowData       = new Object[1];
    }
