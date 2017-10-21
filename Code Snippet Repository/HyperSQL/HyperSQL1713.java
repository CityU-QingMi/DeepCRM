    public HsqlName newConstraintIndexName(HsqlName tableName,
                                           HsqlName constName,
                                           boolean useConstraint) {

        if (constName == null) {
            useConstraint = false;
        }

        if (useConstraint) {
            HsqlName name = new HsqlName(this, constName.name,
                                         SchemaObject.INDEX, false);

            name.schema = tableName.schema;
            name.parent = tableName;

            return name;
        } else {
            String constNameString = constName == null ? null
                                                       : constName.name;

            return newAutoName("IDX", constNameString, tableName.schema,
                               tableName, SchemaObject.INDEX);
        }
    }
