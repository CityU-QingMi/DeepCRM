    public ArrayType(Type dataType, int cardinality) {

        super(Types.SQL_ARRAY, Types.SQL_ARRAY, 0, 0);

        if (dataType == null) {
            dataType = Type.SQL_ALL_TYPES;
        }

        this.dataType       = dataType;
        this.maxCardinality = cardinality;
    }
