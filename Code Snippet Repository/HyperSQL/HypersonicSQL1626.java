    public synchronized void setArray(int parameterIndex,
                                      Array x) throws SQLException {

        checkParameterIndex(parameterIndex);

        Type type = this.parameterMetaData.columnTypes[parameterIndex - 1];

        if (!type.isArrayType()) {
            throw JDBCUtil.sqlException(ErrorCode.X_42561);
        }

        if (x == null) {
            setParameter(parameterIndex, null);

            return;
        }

        Object[] data = null;

        if (x instanceof JDBCArray) {
            data = ((JDBCArray) x).getArrayInternal();
        } else {
            Object object = x.getArray();

            if (object instanceof Object[]) {
                Type     baseType = type.collectionBaseType();
                Object[] array    = (Object[]) object;

                data = new Object[array.length];

                for (int i = 0; i < data.length; i++) {
                    data[i] = baseType.convertJavaToSQL(session, array[i]);
                }
            } else {

                // if foreign data is not Object[]
                throw JDBCUtil.notSupported();
            }
        }
        parameterValues[parameterIndex - 1] = data;
        parameterSet[parameterIndex - 1]    = Boolean.TRUE;
    }
