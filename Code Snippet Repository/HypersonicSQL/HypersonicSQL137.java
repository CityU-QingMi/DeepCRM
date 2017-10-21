    public Object[] getUpdatedArray(Session session, Object[] array,
                                    Object value, boolean copy) {

        if (array == null) {
            throw Error.error(ErrorCode.X_2200E);
        }

        Number index = (Number) nodes[RIGHT].getValue(session);

        if (index == null) {
            throw Error.error(ErrorCode.X_2202E);
        }

        int i = index.intValue() - 1;

        if (i < 0) {
            throw Error.error(ErrorCode.X_2202E);
        }

        if (i >= nodes[LEFT].dataType.arrayLimitCardinality()) {
            throw Error.error(ErrorCode.X_2202E);
        }

        Object[] newArray = array;

        if (i >= array.length) {
            newArray = new Object[i + 1];

            System.arraycopy(array, 0, newArray, 0, array.length);
        } else if (copy) {
            newArray = new Object[array.length];

            System.arraycopy(array, 0, newArray, 0, array.length);
        }

        newArray[i] = value;

        return newArray;
    }
