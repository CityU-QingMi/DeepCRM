    private Statement compileDeclare() {

        Statement      cs;
        ColumnSchema[] variables;

        cs = compileDeclareLocalTableOrNull();

        if (cs != null) {
            return cs;
        }

        variables = readLocalVariableDeclarationOrNull();

        if (variables != null) {
            Object[] args = new Object[]{ variables };

            cs = new StatementSession(StatementTypes.DECLARE_VARIABLE, args);

            return cs;
        }

        cs = compileDeclareCursorOrNull(RangeGroup.emptyArray, false);

        if (cs == null) {
            throw lastError == null ? unexpectedToken()
                                    : lastError;
        }

        return cs;
    }
