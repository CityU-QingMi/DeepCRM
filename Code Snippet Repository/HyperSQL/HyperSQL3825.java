    void getSQL(HsqlArrayList list, OrderedHashSet resolved,
                OrderedHashSet unresolved) {

        // HashMap lists are not persisted with this method
        if (!(map instanceof HashMappedList)) {
            return;
        }

        if (map.isEmpty()) {
            return;
        }

        Iterator it = map.values().iterator();

        if (type == SchemaObject.FUNCTION || type == SchemaObject.PROCEDURE) {
            OrderedHashSet set = new OrderedHashSet();

            while (it.hasNext()) {
                RoutineSchema routineSchema = (RoutineSchema) it.next();

                for (int i = 0; i < routineSchema.routines.length; i++) {
                    Routine routine = routineSchema.routines[i];

                    if (routine.dataImpact == Routine.NO_SQL
                            || routine.dataImpact == Routine.CONTAINS_SQL) {}
                    else {
                        set.add(routine);
                    }
                }
            }

            it = set.iterator();
        }

        addAllSQL(resolved, unresolved, list, it, null);
    }
