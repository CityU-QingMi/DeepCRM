    public Object convertJavaToSQL(SessionInterface session, Object a) {

        Object[] data;
        boolean  convert = false;

        if (a == null) {
            return null;
        }

        if (a instanceof Object[]) {
            data    = (Object[]) a;
            convert = true;
        } else if (a instanceof JDBCArray) {
            data = ((JDBCArray) a).getArrayInternal();
        } else if (a instanceof JDBCArrayBasic) {
            data    = (Object[]) ((JDBCArrayBasic) a).getArray();
            convert = true;
        } else if (a instanceof java.sql.Array) {
            try {
                data    = (Object[]) ((Array) a).getArray();
                convert = true;
            } catch (Exception e) {
                throw Error.error(ErrorCode.X_42561);
            }
        } else {
            throw Error.error(ErrorCode.X_42561);
        }

        if (convert) {
            Object[] array = new Object[data.length];

            for (int i = 0; i < data.length; i++) {
                Object o = dataType.convertJavaToSQL(session, data[i]);

                array[i] = dataType.convertToTypeLimits(session, o);
            }

            return array;
        }

        return data;
    }
