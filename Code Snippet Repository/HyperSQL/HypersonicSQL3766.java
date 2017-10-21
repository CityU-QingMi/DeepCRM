    public Type getIntegralType() {

        switch (typeCode) {

            case Types.SQL_REAL :
            case Types.SQL_FLOAT :
            case Types.SQL_DOUBLE :
                return SQL_NUMERIC_DEFAULT_INT;

            case Types.SQL_NUMERIC :
            case Types.SQL_DECIMAL :
                return scale == 0 ? this
                                  : new NumberType(typeCode, precision, 0);

            default :
                return this;
        }
    }
