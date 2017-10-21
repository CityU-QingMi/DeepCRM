        public OrderedHashSet getSchemaObjectNames() {

            OrderedHashSet set = new OrderedHashSet();

            for (int i = 0; i < usedSequences.size(); i++) {
                SchemaObject object = (SchemaObject) usedSequences.get(i);

                set.add(object.getName());
            }

            for (int i = 0; i < usedObjects.size(); i++) {
                SchemaObject object = (SchemaObject) usedObjects.get(i);

                set.add(object.getName());
            }

            for (int i = 0; i < rangeVariables.size(); i++) {
                RangeVariable range =
                    (RangeVariable) rangeVariables.getValue(i);
                HsqlName name = range.rangeTable.getName();

                if (name.schema != SqlInvariants.SYSTEM_SCHEMA_HSQLNAME) {
                    set.add(range.rangeTable.getName());
                    set.addAll(range.getColumnNames());
                } else if (name.type == SchemaObject.TRANSITION) {
                    set.addAll(range.getColumnNames());
                }
            }

            Routine[] routines = getRoutines();

            for (int i = 0; i < routines.length; i++) {
                set.add(routines[i].getSpecificName());
            }

            return set;
        }
