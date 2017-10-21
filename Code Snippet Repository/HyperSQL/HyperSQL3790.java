    private void removeReferencesFrom(SchemaObject object) {

        HsqlName       name = object.getName();
        OrderedHashSet set  = object.getReferences();

        if (set == null) {
            return;
        }

        for (int i = 0; i < set.size(); i++) {
            HsqlName referenced = (HsqlName) set.get(i);

            if (object instanceof Routine) {
                name = ((Routine) object).getSpecificName();
            }

            referenceMap.remove(referenced, name);
        }
    }
