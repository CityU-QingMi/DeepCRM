    public Constraint(HsqlName uniqueName, HsqlName mainName,
                      HsqlName refName, Table mainTable, Table refTable,
                      int[] mainCols, int[] refCols, Index mainIndex,
                      Index refIndex, int deleteAction, int updateAction) {

        this.name         = refName;
        constType         = SchemaObject.ConstraintTypes.FOREIGN_KEY;
        core              = new ConstraintCore();
        core.uniqueName   = uniqueName;
        core.mainName     = mainName;
        core.refName      = refName;
        core.mainTable    = mainTable;
        core.refTable     = refTable;
        core.mainCols     = mainCols;
        core.refCols      = refCols;
        core.mainIndex    = mainIndex;
        core.refIndex     = refIndex;
        core.deleteAction = deleteAction;
        core.updateAction = updateAction;
    }
