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
            return other.getAggregateType(this);
        }

        throw Error.error(ErrorCode.X_42562);
    }
