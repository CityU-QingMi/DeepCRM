    void resolveColumnReferencesInUnionOrderBy() {

        int orderCount = sortAndSlice.getOrderLength();

        if (orderCount == 0) {
            return;
        }

        String[] unionColumnNames = getColumnNames();

        for (int i = 0; i < orderCount; i++) {
            Expression sort = (Expression) sortAndSlice.exprList.get(i);
            Expression e    = sort.getLeftNode();

            if (e.getType() == OpTypes.VALUE) {
                if (e.getDataType().typeCode == Types.SQL_INTEGER) {
                    int index = ((Integer) e.getValue(null)).intValue();

                    if (0 < index && index <= unionColumnNames.length) {
                        sort.getLeftNode().queryTableColumnIndex = index - 1;

                        continue;
                    }
                }
            } else if (e.getType() == OpTypes.COLUMN) {
                int index = ArrayUtil.find(unionColumnNames,
                                           e.getColumnName());

                if (index >= 0) {
                    sort.getLeftNode().queryTableColumnIndex = index;

                    continue;
                }
            }

            throw Error.error(ErrorCode.X_42576);
        }

        sortAndSlice.prepare(0);
    }
