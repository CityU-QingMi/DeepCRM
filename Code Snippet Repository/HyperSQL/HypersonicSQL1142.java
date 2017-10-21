    void checkConvertColDataType(ColumnSchema oldCol, ColumnSchema newCol) {

        int         colIndex = table.getColumnIndex(oldCol.getName().name);
        RowIterator it       = table.rowIterator(session);

        while (it.next()) {
            Row    row = it.getCurrentRow();
            Object o   = row.getData()[colIndex];

            if (!newCol.isNullable() && o == null) {
                throw Error.error(ErrorCode.X_23502);
            }

            newCol.getDataType().convertToType(session, o,
                                               oldCol.getDataType());
        }
    }
