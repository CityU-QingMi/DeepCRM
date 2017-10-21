    public Type getAggregateType(Type other) {

        if (other == null) {
            return this;
        }

        if (other == SQL_ALL_TYPES) {
            return this;
        }

        if (typeCode == other.typeCode) {
            return this;
        }

        if (other.isCharacterType()) {
            return this;
        }

        switch (other.typeCode) {

            case Types.SQL_GUID :
            case Types.SQL_BINARY :
            case Types.SQL_VARBINARY :
            case Types.SQL_BLOB :
                return this;

            default :
                throw Error.error(ErrorCode.X_42562);
        }
    }
