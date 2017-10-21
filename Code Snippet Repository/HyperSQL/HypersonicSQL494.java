    StatementSchema compileAlterSpecificRoutine() {

        boolean restrict = false;

        readThis(Tokens.SPECIFIC);
        readThis(Tokens.ROUTINE);

        Routine routine =
            (Routine) readSchemaObjectName(SchemaObject.SPECIFIC_ROUTINE);

        routine = routine.duplicate();

        readRoutineCharacteristics(routine);

        restrict = readIfThis(Tokens.RESTRICT);

        if (restrict) {
            OrderedHashSet set = database.schemaManager.getReferencesTo(
                routine.getSpecificName());

            if (!set.isEmpty()) {
                throw Error.parseError(ErrorCode.X_42502, null,
                                       scanner.getLineNumber());
            }
        }

        if (token.tokenType == Tokens.BODY) {
            read();
        } else if (token.tokenType == Tokens.NAME) {
            read();
        }

        readRoutineBody(routine);
        routine.resetAlteredRoutineSettings();
        routine.resolve(session);

        Object[] args = new Object[]{ routine };
        String   sql  = getLastPart();
        StatementSchema cs = new StatementSchema(sql,
            StatementTypes.ALTER_ROUTINE, args, null,
            database.schemaManager.getCatalogNameArray());

        return cs;
    }
