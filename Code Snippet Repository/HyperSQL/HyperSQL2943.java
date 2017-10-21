    public synchronized static Collation getUpperCaseCompareCollation(
            Collation source) {

        if (defaultCollationName.equals(source.name.name)
                || defaultIgnoreCaseCollationName.equals(source.name.name)) {
            return defaultIgnoreCaseCollation;
        }

        if (source.isUpperCaseCompare) {
            return source;
        }

        String name = source.getName().name;

        if (name.contains(" UCC")) {
            return source;
        }

        name = name + " UCC";

        return getCollation(name);
    }
