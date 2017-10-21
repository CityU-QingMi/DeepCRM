    public OrderedHashSet getAllGrantedPrivileges(SchemaObject object) {

        HsqlName name = object.getName();

        if (object instanceof Routine) {
            name = ((Routine) object).getSpecificName();
        }

        Iterator rights = grantedRightsMap.get(name);

        if (rights.hasNext()) {
            OrderedHashSet set = new OrderedHashSet();

            while (rights.hasNext()) {
                set.add(rights.next());
            }

            return set;
        }

        return Right.emptySet;
    }
