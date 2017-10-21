    public void getCascadingReferencesTo(HsqlName object, OrderedHashSet set) {

        readLock.lock();

        try {
            OrderedHashSet newSet = new OrderedHashSet();
            Iterator       it     = referenceMap.get(object);

            while (it.hasNext()) {
                HsqlName name  = (HsqlName) it.next();
                boolean  added = set.add(name);

                if (added) {
                    newSet.add(name);
                }
            }

            for (int i = 0; i < newSet.size(); i++) {
                HsqlName name = (HsqlName) newSet.get(i);

                getCascadingReferencesTo(name, set);
            }
        } finally {
            readLock.unlock();
        }
    }
