    RowSetNavigator getInsertSelectNavigator(Session session) {

        Type[] colTypes  = baseTable.getColumnTypes();
        int[]  columnMap = insertColumnMap;

        //
        Result          result      = queryExpression.getResult(session, 0);
        RowSetNavigator nav         = result.initialiseNavigator();
        Type[]          sourceTypes = result.metaData.columnTypes;
        RowSetNavigatorClient newData =
            new RowSetNavigatorClient(nav.getSize());

        while (nav.next()) {
            Object[] data       = baseTable.getNewRowData(session);
            Object[] sourceData = nav.getCurrent();

            for (int i = 0; i < columnMap.length; i++) {
                int j = columnMap[i];

                if (j == this.overrideUserValue) {
                    continue;
                }

                Type sourceType = sourceTypes[i];

                data[j] = colTypes[j].convertToType(session, sourceData[i],
                                                    sourceType);
            }

            newData.add(data);
        }

        return newData;
    }
