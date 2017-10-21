    public OrderedHashSet getComponents() {

        OrderedHashSet set = new OrderedHashSet();

        set.addAll(constraintList);
        set.addAll(triggerList);

        for (int i = 0; i < indexList.length; i++) {
            if (!indexList[i].isConstraint()) {
                set.add(indexList[i]);
            }
        }

        return set;
    }
