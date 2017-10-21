    public final OrderedHashSet getComponents() {

        if (constraints == null) {
            return null;
        }

        OrderedHashSet set = new OrderedHashSet();

        set.addAll(constraints);

        return set;
    }
