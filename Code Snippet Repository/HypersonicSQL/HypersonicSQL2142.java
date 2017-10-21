    public boolean addAll(OrderedIntHashSet set) {

        int oldSize = size();
        int setSize = set.size();

        for (int i = 0; i < setSize; i++) {
            int value = set.get(i);

            add(value);
        }

        return oldSize != size();
    }
