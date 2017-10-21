    private void removeTableDependentReferences(Table table) {

        OrderedHashSet mainSet = table.getReferencesForDependents();

        for (int i = 0; i < mainSet.size(); i++) {
            HsqlName     name   = (HsqlName) mainSet.get(i);
            SchemaObject object = null;

            switch (name.type) {

                case SchemaObject.CONSTRAINT :
                    object = table.getConstraint(name.name);
                    break;

                case SchemaObject.TRIGGER :
                    object = table.getTrigger(name.name);
                    break;

                case SchemaObject.COLUMN :
                    object = table.getColumn(table.getColumnIndex(name.name));
                    break;

                default :
                    continue;
            }

            removeReferencesFrom(object);
        }
    }
