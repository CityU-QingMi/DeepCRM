    public ColumnSchema duplicate() {

        ColumnSchema copy = new ColumnSchema(columnName, dataType, true,
                                             isPrimaryKey, defaultExpression);

        copy.setNullability(this.nullability);
        copy.setGeneratingExpression(generatingExpression);
        copy.setIdentity(sequence);

        return copy;
    }
