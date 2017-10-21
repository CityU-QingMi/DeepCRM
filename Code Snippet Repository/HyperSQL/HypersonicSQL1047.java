    OrderedHashSet getDependentConstraints(Constraint constraint) {

        OrderedHashSet set = new OrderedHashSet();

        for (int i = 0, size = fkMainConstraints.length; i < size; i++) {
            Constraint c = fkMainConstraints[i];

            if (c.core.uniqueName == constraint.getName()) {
                set.add(c);
            }
        }

        return set;
    }
