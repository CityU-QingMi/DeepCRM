    protected Table readNamedSubqueryOrNull() {

        if (!isSimpleName()) {
            return null;
        }

        TableDerived td = compileContext.getNamedSubQuery(token.tokenString);

        if (td == null) {
            return null;
        }

        read();

        if (td.isRecompiled()) {
            td = td.newDerivedTable(session);
        }

        return td;
    }
