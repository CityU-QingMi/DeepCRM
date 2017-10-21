    Routine readCreateDatabaseAuthenticationFunction() {

        Routine routine = new Routine(SchemaObject.FUNCTION);

        if (token.tokenType == Tokens.NONE) {
            read();

            return null;
        }

        checkIsThis(Tokens.EXTERNAL);
        routine.setLanguage(Routine.LANGUAGE_JAVA);
        routine.setDataImpact(Routine.NO_SQL);
        routine.setName(
            database.nameManager.newHsqlName(
                Tokens.T_AUTHENTICATION, false, SchemaObject.FUNCTION));

        for (int i = 0; i < 3; i++) {
            ColumnSchema column = new ColumnSchema(null, Type.SQL_VARCHAR,
                                                   false, false, null);

            routine.addParameter(column);
        }

        routine.setReturnType(
            new ArrayType(
                Type.SQL_VARCHAR_DEFAULT, ArrayType.defaultArrayCardinality));
        readRoutineBody(routine);
        routine.resolve(session);

        return routine;
    }
