    public RowSetNavigatorData(Session session, QuerySpecification select) {

        this.session       = session;
        this.rangePosition = select.resultRangePosition;
        visibleColumnCount = select.getColumnCount();
        isSimpleAggregate  = select.isAggregated && !select.isGrouped;
        mainIndex          = select.mainIndex;
        fullIndex          = select.fullIndex;
        orderIndex         = select.orderIndex;

        if (select.isGrouped) {
            mainIndex = select.groupIndex;
            rowMap    = new TreeMap(this);
        }

        if (select.idIndex != null) {
            idMap = new LongKeyHashMap();
        }
    }
