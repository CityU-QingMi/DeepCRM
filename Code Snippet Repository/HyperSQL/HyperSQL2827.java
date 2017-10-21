    public Type getCombinedType(Session session, Type other, int operation) {

        if (operation != OpTypes.CONCAT) {
            return getAggregateType(other);
        }

        Type newType;
        long newPrecision = this.precision + other.precision;

        switch (other.typeCode) {

            case Types.SQL_ALL_TYPES :
                return this;

            case Types.SQL_BIT :
            case Types.SQL_BIT_VARYING :
                newPrecision = this.precision + (other.precision + 7) / 8;
                newType      = this;
                break;

            case Types.SQL_GUID :
            case Types.SQL_BINARY :
                newType = this;
                break;

            case Types.SQL_VARBINARY :
                newType = (typeCode == Types.SQL_BLOB) ? this
                                                       : other;
                break;

            case Types.SQL_BLOB :
                newType = other;
                break;

            default :
                throw Error.error(ErrorCode.X_42561);
        }

        if (newPrecision > maxBinaryPrecision) {
            if (typeCode == Types.SQL_BINARY) {

                // Standard disallows type length reduction
                throw Error.error(ErrorCode.X_42570);
            } else if (typeCode == Types.SQL_VARBINARY) {
                newPrecision = maxBinaryPrecision;
            }
        }

        return getBinaryType(newType.typeCode, newPrecision);
    }
