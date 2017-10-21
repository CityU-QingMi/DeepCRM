    void registerConstraintNames(HsqlArrayList constraints) {

        for (int i = 0; i < constraints.size(); i++) {
            Constraint c = (Constraint) constraints.get(i);

            switch (c.getConstraintType()) {

                case SchemaObject.ConstraintTypes.PRIMARY_KEY :
                case SchemaObject.ConstraintTypes.UNIQUE :
                case SchemaObject.ConstraintTypes.CHECK :
                    database.schemaManager.addSchemaObject(c);
            }
        }
    }
