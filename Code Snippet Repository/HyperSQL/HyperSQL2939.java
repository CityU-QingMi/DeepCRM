    private Collation(boolean simple) {

        String nameString = simple ? defaultCollationName
                                   : defaultIgnoreCaseCollationName;

        locale = Locale.ENGLISH;
        name = HsqlNameManager.newInfoSchemaObjectName(nameString, false,
                SchemaObject.COLLATION);
        isUnicodeSimple = simple;
        isFinal         = true;
    }
