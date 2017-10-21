    public OrderedHashSet getReferencesTo(HsqlName table, HsqlName column) {

        readLock.lock();

        try {
            OrderedHashSet set = new OrderedHashSet();
            Iterator       it  = referenceMap.get(table);

            while (it.hasNext()) {
                HsqlName       name       = (HsqlName) it.next();
                SchemaObject   object     = getSchemaObject(name);
                OrderedHashSet references = object.getReferences();

                if (references.contains(column)) {
                    set.add(name);
                }
            }

            it = referenceMap.get(column);

            while (it.hasNext()) {
                HsqlName name = (HsqlName) it.next();

                set.add(name);
            }

            return set;
        } finally {
            readLock.unlock();
        }
    }
