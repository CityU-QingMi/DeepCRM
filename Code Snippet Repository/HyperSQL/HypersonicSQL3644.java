    private Collation(String name, String language, String country,
                      int strength, int decomposition, boolean ucc) {

        locale   = new Locale(language, country);
        collator = Collator.getInstance(locale);

        if (strength >= 0) {
            collator.setStrength(strength);
        }

        if (decomposition >= 0) {
            collator.setDecomposition(decomposition);
        }

        strength        = collator.getStrength();
        isUnicodeSimple = false;
        this.name = HsqlNameManager.newInfoSchemaObjectName(name, true,
                SchemaObject.COLLATION);
        charset            = Charset.SQL_TEXT;
        isUpperCaseCompare = ucc;
        isFinal            = true;
    }
