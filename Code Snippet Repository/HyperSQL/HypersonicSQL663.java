    public OrderedHashSet getReferences() {

        OrderedHashSet set = new OrderedHashSet();

        for (int i = 0; i < routines.length; i++) {
            set.addAll(routines[i].getReferences());
        }

        return set;
    }
