    Routine readCreatePasswordCheckFunction() {

        Routine routine = new Routine(SchemaObject.FUNCTION);

        if (token.tokenType == Tokens.NONE) {
            read();

            return null;
        } else if (token.tokenType == Tokens.EXTERNAL) {
            routine.setLanguage(Routine.LANGUAGE_JAVA);
            routine.setDataImpact(Routine.NO_SQL);
        } else {
            routine.setLanguage(Routine.LANGUAGE_SQL);
            routine.setDataImpact(Routine.CONTAINS_SQL);
        }

        HsqlName hsqlName = database.nameManager.newHsqlName(Tokens.T_PASSWORD,
            false, SchemaObject.FUNCTION);

        hsqlName.setSchemaIfNull(SqlInvariants.SYSTEM_SCHEMA_HSQLNAME);
        routine.setName(hsqlName);

        hsqlName = database.nameManager.newHsqlName(Tokens.T_PASSWORD, false,
                SchemaObject.PARAMETER);

        ColumnSchema column = new ColumnSchema(hsqlName, Type.SQL_VARCHAR,
                                               false, false, null);

        routine.addParameter(column);
        routine.setReturnType(Type.SQL_BOOLEAN);
        readRoutineBody(routine);
        routine.resolve(session);

        return routine;
    }
