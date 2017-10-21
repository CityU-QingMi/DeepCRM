    private Result getExplainResult(Session session) {

        Result result = Result.newSingleColumnStringResult("OPERATION",
            describe(session));
        OrderedHashSet set = getReferences();

        result.navigator.add(new Object[]{ "Object References" });

        for (int i = 0; i < set.size(); i++) {
            HsqlName name = (HsqlName) set.get(i);

            result.navigator.add(new Object[]{
                name.getSchemaQualifiedStatementName() });
        }

        result.navigator.add(new Object[]{ "Read Locks" });

        for (int i = 0; i < readTableNames.length; i++) {
            HsqlName name = readTableNames[i];

            result.navigator.add(new Object[]{
                name.getSchemaQualifiedStatementName() });
        }

        result.navigator.add(new Object[]{ "WriteLocks" });

        for (int i = 0; i < writeTableNames.length; i++) {
            HsqlName name = writeTableNames[i];

            result.navigator.add(new Object[]{
                name.getSchemaQualifiedStatementName() });
        }

        return result;
    }
