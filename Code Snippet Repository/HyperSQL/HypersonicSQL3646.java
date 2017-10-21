    public synchronized static Collation getCollation(String name) {

        Collation collation = (Collation) dbNameToCollation.get(name);

        if (collation != null) {
            return collation;
        }

        collation = getNewCollation(name);

        dbNameToCollation.put(name, collation);

        return collation;
    }
