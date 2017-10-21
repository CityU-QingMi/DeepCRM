    public OrderedHashSet getReferences() {

        OrderedHashSet set = new OrderedHashSet();

        for (int i = 0; i < constraints.length; i++) {
            OrderedHashSet subSet = constraints[i].getReferences();

            if (subSet != null) {
                set.addAll(subSet);
            }
        }

        return set;
    }
