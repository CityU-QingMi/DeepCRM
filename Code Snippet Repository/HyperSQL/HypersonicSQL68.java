    void setColumnsIndexes(Table table) {

        if (constType == SchemaObject.ConstraintTypes.FOREIGN_KEY) {
            if (mainColSet == null) {
                core.mainCols = core.mainTable.getPrimaryKey();

                if (core.mainCols == null) {
                    throw Error.error(ErrorCode.X_42581);
                }
            } else if (core.mainCols == null) {
                core.mainCols = core.mainTable.getColumnIndexes(mainColSet);
            }

            if (core.refCols == null) {
                core.refCols = table.getColumnIndexes(refColSet);
            }

            for (int i = 0; i < core.refCols.length; i++) {
                Type dataType = table.getColumn(core.refCols[i]).getDataType();

                if (dataType.isLobType()) {
                    throw Error.error(ErrorCode.X_42534);
                }
            }

            if (core.mainCols.length != core.refCols.length) {
                throw Error.error(ErrorCode.X_42593);
            }
        } else if (mainColSet != null) {
            core.mainCols = table.getColumnIndexes(mainColSet);

            for (int i = 0; i < core.mainCols.length; i++) {
                Type dataType =
                    table.getColumn(core.mainCols[i]).getDataType();

                if (dataType.isLobType()) {
                    throw Error.error(ErrorCode.X_42534);
                }
            }
        }
    }
