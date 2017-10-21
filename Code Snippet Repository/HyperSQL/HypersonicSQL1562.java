    public Array createArrayOf(String typeName,
                               Object[] elements) throws SQLException {

        checkClosed();

        if (typeName == null) {
            throw JDBCUtil.nullArgument();
        }
        typeName = typeName.toUpperCase();

        int typeCode = Type.getTypeNr(typeName);

        if (typeCode < 0) {
            throw JDBCUtil.invalidArgument(typeName);
        }

        Type type = Type.getDefaultType(typeCode);

        if (type.isArrayType() || type.isLobType() || type.isRowType()) {
            throw JDBCUtil.invalidArgument(typeName);
        }

        Object[] newData = new Object[elements.length];

        try {
            for (int i = 0; i < elements.length; i++) {
                Object o = type.convertJavaToSQL(sessionProxy, elements[i]);

                newData[i] = type.convertToTypeLimits(sessionProxy, o);
            }
        } catch (HsqlException e) {
            throw JDBCUtil.sqlException(e);
        }

        return new JDBCArray(newData, type, this);
    }
