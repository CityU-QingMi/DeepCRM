    public byte getNullability() {

        switch (opType) {

            case OpTypes.COLUMN :
                if (nullability == SchemaObject.Nullability.NULLABLE_UNKNOWN) {
                    return column.getNullability();
                }

                return nullability;

            case OpTypes.COALESCE :
            case OpTypes.SEQUENCE :
            case OpTypes.ROWNUM :
                return SchemaObject.Nullability.NO_NULLS;

            default :
                return SchemaObject.Nullability.NULLABLE_UNKNOWN;
        }
    }
