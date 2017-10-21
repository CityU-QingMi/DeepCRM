    public TriggerDef(HsqlNameManager.HsqlName name, int when, int operation,
                      boolean forEachRow, Table table, Table[] transitions,
                      RangeVariable[] rangeVars, Expression condition,
                      String conditionSQL, int[] updateColumns) {

        this.name          = name;
        this.actionTiming  = when;
        this.operationType = operation;
        this.forEachRow    = forEachRow;
        this.table         = table;
        this.transitions   = transitions;
        this.rangeVars     = rangeVars;
        this.condition     = condition == null ? Expression.EXPR_TRUE
                                               : condition;
        this.updateColumns = updateColumns;
        this.conditionSQL  = conditionSQL;
        hasTransitionRanges = rangeVars[OLD_ROW] != null
                              || rangeVars[NEW_ROW] != null;
        hasTransitionTables = transitions[OLD_TABLE] != null
                              || transitions[NEW_TABLE] != null;

        setUpIndexesAndTypes();
    }
