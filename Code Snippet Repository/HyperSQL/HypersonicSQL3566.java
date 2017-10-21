    public Type getCombinedType(Session session, Type other, int operation) {

        if (operation != OpTypes.CONCAT) {
            return getAggregateType(other);
        }

        Type newType;
        long newPrecision = precision + other.precision;

        switch (other.typeCode) {

            case Types.SQL_ALL_TYPES :
                return this;

            case Types.SQL_BIT :
                newType = this;
                break;

            case Types.SQL_BIT_VARYING :
                newType = other;
                break;

            case Types.SQL_BINARY :
            case Types.SQL_VARBINARY :
            case Types.SQL_BLOB :
                return other.getCombinedType(session, this, operation);

            default :
                throw Error.error(ErrorCode.X_42562);
        }

        if (newPrecision > maxBitPrecision) {
            if (typeCode == Types.SQL_BIT) {

                // Standard disallows type length reduction
                throw Error.error(ErrorCode.X_42570);
            }

            newPrecision = maxBitPrecision;
        }

        return getBitType(newType.typeCode, newPrecision);
    }
