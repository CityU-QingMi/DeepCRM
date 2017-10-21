    public Object convertToType(SessionInterface session, Object a,
                                Type otherType) {

        if (a == null) {
            return null;
        }

        if (otherType == null) {
            return a;
        }

        if (!otherType.isRowType()) {
            throw Error.error(ErrorCode.X_42562);
        }

        Type[] otherTypes = ((RowType) otherType).getTypesArray();

        if (dataTypes.length != otherTypes.length) {
            throw Error.error(ErrorCode.X_42564);
        }

        Object[] arra = (Object[]) a;
        Object[] arrb = new Object[arra.length];

        for (int i = 0; i < arra.length; i++) {
            arrb[i] = dataTypes[i].convertToType(session, arra[i],
                                                 otherTypes[i]);
        }

        return arrb;
    }
