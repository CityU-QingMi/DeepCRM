    public Object convertToTypeLimits(SessionInterface session, Object a) {

        if (a == null) {
            return null;
        }

        Object[] arra = (Object[]) a;

        if (arra.length > maxCardinality) {
            throw Error.error(ErrorCode.X_2202F);
        }

        Object[] arrb = new Object[arra.length];

        for (int i = 0; i < arra.length; i++) {
            arrb[i] = dataType.convertToTypeLimits(session, arra[i]);
        }

        return arrb;
    }
