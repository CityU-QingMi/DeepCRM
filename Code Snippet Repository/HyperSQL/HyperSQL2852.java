    public Type getAggregateType(Type other) {

        if (other == null) {
            return this;
        }

        if (other == SQL_ALL_TYPES) {
            return this;
        }

        if (typeCode == other.typeCode) {
            return precision >= other.precision ? this
                                                : other;
        }

        switch (other.typeCode) {

            case Types.SQL_BIT :
                return precision >= other.precision ? this
                                                    : getBitType(typeCode,
                                                    other.precision);

            case Types.SQL_BIT_VARYING :
                return other.precision >= precision ? other
                                                    : getBitType(
                                                    other.typeCode, precision);

            case Types.SQL_BINARY :
            case Types.SQL_VARBINARY :
            case Types.SQL_BLOB :
                return other;

            default :
                throw Error.error(ErrorCode.X_42562);
        }
    }
