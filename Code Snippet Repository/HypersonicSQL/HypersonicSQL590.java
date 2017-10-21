    public RangeVariable(Table table, SimpleName alias,
                         OrderedHashSet columnList,
                         SimpleName[] columnNameList,
                         CompileContext compileContext) {

        rangeType        = TABLE_RANGE;
        rangeTable       = table;
        tableAlias       = alias;
        columnAliases    = columnList;
        columnAliasNames = columnNameList;
        joinConditions = new RangeVariableConditions[]{
            new RangeVariableConditions(this, true) };
        whereConditions = new RangeVariableConditions[]{
            new RangeVariableConditions(this, false) };

        compileContext.registerRangeVariable(this);

        if (rangeTable.getColumnCount() != 0) {
            setRangeTableVariables();
        }
    }
