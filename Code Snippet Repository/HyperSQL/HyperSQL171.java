    OrderedHashSet dropConstraintsAndIndexes(OrderedHashSet tableSet,
            OrderedHashSet dropConstraintSet, OrderedHashSet dropIndexSet) {

        OrderedHashSet newSet = new OrderedHashSet();

        for (int i = 0; i < tableSet.size(); i++) {
            Table      t  = (Table) tableSet.get(i);
            TableWorks tw = new TableWorks(session, t);

            tw.dropConstraintsAndIndexes(dropConstraintSet, dropIndexSet);
            newSet.add(tw.getTable());
        }

        return newSet;
    }
