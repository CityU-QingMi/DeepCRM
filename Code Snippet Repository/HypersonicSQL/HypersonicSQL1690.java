    protected Object getColumnInType(int columnIndex,
                                     Type targetType) throws SQLException {

        Object value = getColumnValue(columnIndex);
        Type sourceType;

        if (value == null) {
            return null;
        }

        sourceType = resultMetaData.columnTypes[columnIndex - 1];

        if (translateTTIType && targetType.isIntervalType()) {
            targetType = ((IntervalType) targetType).getCharacterType();
        }


        if (sourceType.typeCode != targetType.typeCode) {
            try {
                value = targetType.convertToTypeJDBC(session, value,
                        sourceType);
            } catch (Exception e) {
                String stringValue = (value instanceof Number
                                      || value
                                         instanceof String) ? value.toString()
                        : "instance of " + value.getClass().getName();
                String msg = "from SQL type " + sourceType.getNameString()
                             + " to " + targetType.getJDBCClassName()
                             + ", value: " + stringValue;

                JDBCUtil.throwError(Error.error(ErrorCode.X_42561, msg));
            }
        }

        return value;
    }
