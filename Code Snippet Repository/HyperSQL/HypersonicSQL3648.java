    public void setCollation(String newName, boolean padSpace) {

        if (isFinal) {
            throw Error.error(ErrorCode.X_42503, newName);
        }

        Collation newCollation = Collation.getCollation(newName);

        this.name.rename(newCollation.name.name, true);

        this.locale          = newCollation.locale;
        this.collator        = newCollation.collator;
        this.isUnicodeSimple = newCollation.isUnicodeSimple;
        this.padSpace        = padSpace;
    }
