    static boolean isChildObjectResolved(SchemaObject object,
                                         OrderedHashSet resolved) {

        OrderedHashSet refs = object.getReferences();

        for (int i = 0; i < refs.size(); i++) {
            HsqlName name = (HsqlName) refs.get(i);

            if (SqlInvariants.isSchemaNameSystem(name)) {
                continue;
            }

            if (!resolved.contains(name)) {
                return false;
            }
        }

        return true;
    }
