    public TriggerDefSQL(HsqlNameManager.HsqlName name, int when,
                         int operation, boolean forEachRow, Table table,
                         Table[] transitions, RangeVariable[] rangeVars,
                         Expression condition, String conditionSQL,
                         int[] updateColumns, Routine routine) {

        super(name, when, operation, forEachRow, table, transitions,
              rangeVars, condition, conditionSQL, updateColumns);

        this.routine    = routine;
        this.references = routine.getReferences();
    }
