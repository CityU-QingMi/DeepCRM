    public void setRangeTableVariables() {

        if (columnAliasNames != null
                && rangeTable.getColumnCount() != columnAliasNames.length) {
            throw Error.error(ErrorCode.X_42593);
        }

        columnsInGroupBy              = rangeTable.getNewColumnCheckList();
        usedColumns                   = rangeTable.getNewColumnCheckList();
        joinConditions[0].rangeIndex  = rangeTable.getPrimaryIndex();
        whereConditions[0].rangeIndex = rangeTable.getPrimaryIndex();
    }
