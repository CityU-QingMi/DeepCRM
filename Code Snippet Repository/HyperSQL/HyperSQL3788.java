    private void addReferencesFrom(SchemaObject object) {

        OrderedHashSet set  = object.getReferences();
        HsqlName       name = object.getName();

        if (set == null) {
            return;
        }

        for (int i = 0; i < set.size(); i++) {
            HsqlName referenced = (HsqlName) set.get(i);

            if (object instanceof Routine) {
                name = ((Routine) object).getSpecificName();
            }

            referenceMap.put(referenced, name);
        }
    }
