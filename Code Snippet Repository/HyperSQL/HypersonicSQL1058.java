    public void createPrimaryKeyConstraint(HsqlName indexName, int[] columns,
                                           boolean columnsNotNull) {

        createPrimaryKey(indexName, columns, columnsNotNull);

        Constraint c =
            new Constraint(indexName, this, getPrimaryIndex(),
                           SchemaObject.ConstraintTypes.PRIMARY_KEY);

        addConstraint(c);
    }
