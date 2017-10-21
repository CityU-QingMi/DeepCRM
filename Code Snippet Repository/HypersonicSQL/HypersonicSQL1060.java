    private void setSingleColumnTypeVars(int i) {

        ColumnSchema column   = getColumn(i);
        Type         dataType = column.getDataType();

        colTypes[i]         = dataType;
        colNotNull[i]       = column.isPrimaryKey() || !column.isNullable();
        defaultColumnMap[i] = i;

        if (column.isIdentity()) {
            identitySequence = column.getIdentitySequence();
            identityColumn   = i;
        } else if (identityColumn == i) {
            identitySequence = null;
            identityColumn   = -1;
        }

        colDefaults[i]  = column.getDefaultExpression();
        colGenerated[i] = column.isGenerated();
        colUpdated[i]   = column.isAutoUpdate();
    }
