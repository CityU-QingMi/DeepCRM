    public Routine(Table table, RangeVariable[] ranges, int impact,
                   int triggerType, int operationType) {

        routineType           = SchemaObject.TRIGGER;
        returnType            = Type.SQL_ALL_TYPES;
        dataImpact            = impact;
        this.ranges           = ranges;
        this.triggerTable     = table;
        this.triggerType      = triggerType;
        this.triggerOperation = operationType;
    }
