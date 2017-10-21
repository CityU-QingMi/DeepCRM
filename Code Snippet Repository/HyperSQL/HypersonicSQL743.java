    public HsqlArrayList getSQLArray(OrderedHashSet resolved,
                                     OrderedHashSet unresolved) {

        HsqlArrayList list      = new HsqlArrayList();
        String        setSchema = getSetSchemaSQL(name);

        list.add(setSchema);
        sequenceLookup.getSQL(list, resolved, unresolved);
        tableLookup.getSQL(list, resolved, unresolved);
        functionLookup.getSQL(list, resolved, unresolved);
        procedureLookup.getSQL(list, resolved, unresolved);
        referenceLookup.getSQL(list, resolved, unresolved);

        if (list.size() == 1) {
            list.clear();
        }

        return list;
    }
