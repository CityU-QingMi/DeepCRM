    public static Type getAggregateType(Type add, Type existing) {

        if (existing == null || existing.typeCode == Types.SQL_ALL_TYPES) {
            return add;
        }

        if (add == null || add.typeCode == Types.SQL_ALL_TYPES) {
            return existing;
        }

        return existing.getAggregateType(add);
    }
