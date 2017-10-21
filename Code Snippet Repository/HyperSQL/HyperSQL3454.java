    ColumnSchema readRoutineParameter(Routine routine, boolean isParam) {

        HsqlName hsqlName = null;
        int parameterMode = readRoutineParameterMode(routine.routineType,
            routine.isAggregate);

        if (!isReservedKey()) {
            hsqlName = readNewDependentSchemaObjectName(routine.getName(),
                    SchemaObject.PARAMETER);
        }

        Type typeObject = readTypeDefinition(false, true);
        ColumnSchema column = new ColumnSchema(hsqlName, typeObject, true,
                                               false, null);

        if (isParam) {
            column.setParameterMode((byte) parameterMode);
        }

        return column;
    }
