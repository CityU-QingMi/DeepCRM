    private Object getColumnInType(int columnIndex,
                                   Type targetType) throws SQLException {

        checkGetParameterIndex(columnIndex);

        Type   sourceType;
        Object value;

        sourceType = parameterTypes[--columnIndex];
        value      = parameterValues[columnIndex];

        if (trackNull(value)) {
            return null;
        }

        if (sourceType.typeCode != targetType.typeCode) {
            try {
                value = targetType.convertToTypeJDBC(session, value,
                        sourceType);
            } catch (HsqlException e) {
                String stringValue =
                    (value instanceof Number || value instanceof String
                     || value instanceof java.util.Date) ? value.toString()
                        : "instance of " + value.getClass().getName();
                String msg = "from SQL type " + sourceType.getNameString()
                             + " to " + targetType.getJDBCClassName()
                             + ", value: " + stringValue;
                HsqlException err = Error.error(ErrorCode.X_42561, msg);

                throw JDBCUtil.sqlException(err, e);
            }
        }

        return value;
    }
