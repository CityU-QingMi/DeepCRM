    public Object getValue(Session session) {

        Object[] array = (Object[]) nodes[LEFT].getValue(session);

        if (array == null) {
            return null;
        }

        Number index = (Number) nodes[RIGHT].getValue(session);

        if (index == null) {
            return null;
        }

        if (index.intValue() < 1 || index.intValue() > array.length) {
            throw Error.error(ErrorCode.X_2202E);
        }

        return array[index.intValue() - 1];
    }
