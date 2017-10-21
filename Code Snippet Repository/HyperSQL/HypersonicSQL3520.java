    public Type getCombinedType(Session session, Type other, int operation) {

        ArrayType type = (ArrayType) getAggregateType(other);

        if (other == null) {
            return type;
        }

        if (operation != OpTypes.CONCAT) {
            return type;
        }

        if (type.maxCardinality == ArrayType.defaultLargeArrayCardinality) {
            return type;
        }

        long card = (long) ((ArrayType) other).maxCardinality + maxCardinality;

        if (card > ArrayType.defaultLargeArrayCardinality) {
            card = ArrayType.defaultLargeArrayCardinality;
        }

        return new ArrayType(dataType, (int) card);
    }
