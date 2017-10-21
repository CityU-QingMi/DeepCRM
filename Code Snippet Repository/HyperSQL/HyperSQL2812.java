    public Object convertToType(SessionInterface session, Object a,
                                Type otherType) {

        if (a == null) {
            return null;
        }

        if (otherType == null) {
            return a;
        }

        if (!otherType.isArrayType()) {
            throw Error.error(ErrorCode.X_42562);
        }

        Object[] arra = (Object[]) a;

        if (arra.length > maxCardinality) {
            throw Error.error(ErrorCode.X_2202F);
        }

        Type otherComponent = otherType.collectionBaseType();

        if (dataType.equals(otherComponent)) {
            return a;
        }

        Object[] arrb = new Object[arra.length];

        for (int i = 0; i < arra.length; i++) {
            arrb[i] = dataType.convertToType(session, arra[i], otherComponent);
        }

        return arrb;
    }
