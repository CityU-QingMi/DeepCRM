    public OrderedHashSet getAllDirectPrivileges(SchemaObject object) {

        if (object.getOwner() == this) {
            OrderedHashSet set = new OrderedHashSet();

            set.add(ownerRights);

            return set;
        }

        HsqlName name = object.getName();

        if (object instanceof Routine) {
            name = ((Routine) object).getSpecificName();
        }

        Iterator rights = directRightsMap.get(name);

        if (rights.hasNext()) {
            OrderedHashSet set = new OrderedHashSet();

            while (rights.hasNext()) {
                set.add(rights.next());
            }

            return set;
        }

        return Right.emptySet;
    }
