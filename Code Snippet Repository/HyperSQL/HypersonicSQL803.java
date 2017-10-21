    public void checkColumnIsReferenced(HsqlName tableName, HsqlName name) {

        OrderedHashSet set = getReferencesTo(tableName, name);

        if (!set.isEmpty()) {
            HsqlName objectName = (HsqlName) set.get(0);

            throw Error.error(ErrorCode.X_42502,
                              objectName.getSchemaQualifiedStatementName());
        }
    }
