    Statement compileAlterRoutine() {

        readThis(Tokens.ROUTINE);

        RoutineSchema routine =
            (RoutineSchema) readSchemaObjectName(SchemaObject.ROUTINE);

        readThis(Tokens.RENAME);
        readThis(Tokens.TO);

        return compileRenameObject(routine.getName(), routine.getName().type);
    }
