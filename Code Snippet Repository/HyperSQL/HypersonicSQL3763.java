    public Object modulo(Session session, Object a, Object b, Type otherType) {

        if (!otherType.isNumberType()) {
            throw Error.error(ErrorCode.X_42561);
        }

        a = truncate(a, scale);
        b = ((NumberType) otherType).truncate(b, otherType.scale);

        Object temp = divide(null, a, b);

        switch (typeCode) {

            case Types.SQL_REAL :
            case Types.SQL_FLOAT :
            case Types.SQL_DOUBLE :
            case Types.SQL_NUMERIC :
            case Types.SQL_DECIMAL :
                temp = truncate(temp, 0);
        }

        temp = multiply(temp, b);
        temp = subtract(session, a, temp, this);

        return otherType.convertToType(null, temp, this);
    }
