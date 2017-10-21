    public OrderedHashSet getReferences() {

        OrderedHashSet set = new OrderedHashSet();

        if (identitySequence != null && identitySequence.getName() != null) {
            set.add(identitySequence.getName());
        }

        return set;
    }
