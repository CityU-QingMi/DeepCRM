    public void addSimpleObjects(OrderedHashSet unresolved) {

        Iterator it = specificRoutineLookup.map.values().iterator();

        while (it.hasNext()) {
            Routine routine = (Routine) it.next();

            if (routine.dataImpact == Routine.NO_SQL
                    || routine.dataImpact == Routine.CONTAINS_SQL) {
                unresolved.add(routine);
            }
        }

        unresolved.addAll(typeLookup.map.values());
        unresolved.addAll(charsetLookup.map.values());
        unresolved.addAll(collationLookup.map.values());
    }
