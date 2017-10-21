    public Type getAggregateType(Type other) {

        if (other == null) {
            return this;
        }

        if (other == SQL_ALL_TYPES) {
            return this;
        }

        if (this == other) {
            return this;
        }

        if (!other.isArrayType()) {
            throw Error.error(ErrorCode.X_42562);
        }

        Type otherComponent = other.collectionBaseType();

        if (dataType.equals(otherComponent)) {
            return ((ArrayType) other).maxCardinality > maxCardinality ? other
                                                                       : this;
        }

        Type newComponent = dataType.getAggregateType(otherComponent);
        int cardinality = ((ArrayType) other).maxCardinality > maxCardinality
                          ? ((ArrayType) other).maxCardinality
                          : maxCardinality;

        return new ArrayType(newComponent, cardinality);
    }
