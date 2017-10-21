        protected Object getColumnInType(int columnIndex,
                Type targetType) throws SQLException {

            Object[] rowData = getCurrent();
            Type     sourceType;
            Object   value;

            checkColumn(columnIndex);

            sourceType = resultMetaData.columnTypes[--columnIndex];
            value      = rowData[columnIndex];

            if (trackNull(value)) {
                return null;
            }

            if (sourceType.typeCode != targetType.typeCode) {
                JDBCUtil.throwError(Error.error(ErrorCode.X_42561));
            }

            return value;
        }
