    private void setReferences() {

        if (references != null) {
            references.clear();
        }

        if (generatedColumnReferences != null) {
            generatedColumnReferences.clear();
        }

        if (dataType.isDomainType() || dataType.isDistinctType()) {
            HsqlName name = dataType.getName();

            if (references == null) {
                references = new OrderedHashSet();
            }

            references.add(name);
        }

        if (generatingExpression != null) {
            OrderedHashSet set = new OrderedHashSet();

            generatingExpression.collectObjectNames(set);

            Iterator it = set.iterator();

            while (it.hasNext()) {
                HsqlName name = (HsqlName) it.next();

                if (name.type == SchemaObject.COLUMN
                        || name.type == SchemaObject.TABLE) {
                    if (name.type == SchemaObject.COLUMN) {
                        if (generatedColumnReferences == null) {
                            generatedColumnReferences = new OrderedHashSet();
                        }

                        generatedColumnReferences.add(name);
                    }
                } else {
                    if (references == null) {
                        references = new OrderedHashSet();
                    }

                    references.add(name);
                }
            }
        }
    }
