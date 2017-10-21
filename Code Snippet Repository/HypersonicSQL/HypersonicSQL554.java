    void createTable(Session session) {

        createResultTable(session);

        mainIndex = resultTable.getPrimaryIndex();

        if (sortAndSlice.hasOrder() && !sortAndSlice.skipSort) {
            orderIndex = sortAndSlice.getNewIndex(session, resultTable);
        }

        if (isDistinctSelect || isFullOrder) {
            createFullIndex(session);
        }

        if (isGrouped) {
            int[] groupCols = new int[groupByColumnCount];

            for (int i = 0; i < groupByColumnCount; i++) {
                groupCols[i] = indexLimitRowId + i;
            }

            groupIndex = resultTable.createAndAddIndexStructure(session, null,
                    groupCols, null, null, false, false, false);
        } else if (isAggregated) {
            groupIndex = mainIndex;
        }

        if (isUpdatable && view == null) {
            int[] idCols = new int[]{ indexLimitVisible };

            idIndex = resultTable.createAndAddIndexStructure(session, null,
                    idCols, null, null, false, false, false);
        }
    }
