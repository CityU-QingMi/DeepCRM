    public OrderedHashSet getReferences() {

        switch (constType) {

            case SchemaObject.ConstraintTypes.CHECK :
                OrderedHashSet refs = new OrderedHashSet();

                check.collectObjectNames(refs);

                for (int j = refs.size() - 1; j >= 0; j--) {
                    HsqlName name = (HsqlName) refs.get(j);

                    if (name.type == SchemaObject.COLUMN
                            || name.type == SchemaObject.TABLE) {
                        refs.remove(j);
                    }
                }

                return refs;

            case SchemaObject.ConstraintTypes.FOREIGN_KEY :
                OrderedHashSet set = new OrderedHashSet();

                set.add(core.uniqueName);

                return set;
        }

        return new OrderedHashSet();
    }
