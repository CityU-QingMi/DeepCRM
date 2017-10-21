    public OrderedHashSet getReferencesTo(HsqlName object) {

        readLock.lock();

        try {
            OrderedHashSet set = new OrderedHashSet();
            Iterator       it  = referenceMap.get(object);

            while (it.hasNext()) {
                HsqlName name = (HsqlName) it.next();

                set.add(name);
            }

            return set;
        } finally {
            readLock.unlock();
        }
    }
