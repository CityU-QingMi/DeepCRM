        public Routine[] getRoutines() {

            if (callProcedure == null && usedRoutines.size() == 0) {
                return Routine.emptyArray;
            }

            OrderedHashSet set = new OrderedHashSet();

            for (int i = 0; i < usedRoutines.size(); i++) {
                FunctionSQLInvoked function =
                    (FunctionSQLInvoked) usedRoutines.get(i);

                set.add(function.routine);
            }

            if (callProcedure != null) {
                set.add(callProcedure);
            }

            Routine[] array = new Routine[set.size()];

            set.toArray(array);

            return array;
        }
