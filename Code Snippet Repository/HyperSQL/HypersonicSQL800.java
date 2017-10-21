    public void getCascadingReferencesToSchema(HsqlName schema,
            OrderedHashSet set) {

        Iterator mainIterator = referenceMap.keySet().iterator();

        while (mainIterator.hasNext()) {
            HsqlName name = (HsqlName) mainIterator.next();

            if (name.schema != schema) {
                continue;
            }

            getCascadingReferencesTo(name, set);
        }

        for (int i = set.size() - 1; i >= 0; i--) {
            HsqlName name = (HsqlName) set.get(i);

            if (name.schema == schema) {
                set.remove(i);
            }
        }
    }
