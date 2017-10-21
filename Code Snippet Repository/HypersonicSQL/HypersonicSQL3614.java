    public Type getCombinedType(Session session, Type other, int operation) {

        if (operation != OpTypes.CONCAT) {
            return getAggregateType(other);
        }

        Type newType;
        long newPrecision = this.precision + other.precision;

        switch (other.typeCode) {

            case Types.SQL_ALL_TYPES :
                return this;

            case Types.SQL_CHAR :
                newType = this;
                break;

            case Types.SQL_VARCHAR :
                newType = (typeCode == Types.SQL_CLOB) ? this
                                                       : other;
                break;

            case Types.SQL_CLOB :
                newType = other;
                break;

            default :
                throw Error.error(ErrorCode.X_42562);
        }

        if (newPrecision > maxCharPrecision) {
            if (typeCode == Types.SQL_BINARY) {

                // Standard disallows type length reduction
                // throw Error.error(ErrorCode.X_42570);
                newPrecision = maxCharPrecision;
            } else if (typeCode == Types.SQL_CHAR) {
                newPrecision = maxCharPrecision;
            } else if (typeCode == Types.SQL_VARCHAR) {
                newPrecision = maxCharPrecision;
            }
        }

        return getCharacterType(newType.typeCode, newPrecision, collation);
    }
