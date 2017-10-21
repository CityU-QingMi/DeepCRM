    ConstraintCore duplicate() {

        ConstraintCore copy = new ConstraintCore();

        copy.refName      = refName;
        copy.mainName     = mainName;
        copy.uniqueName   = uniqueName;
        copy.mainTable    = mainTable;
        copy.mainCols     = mainCols;
        copy.mainIndex    = mainIndex;
        copy.refTable     = refTable;
        copy.refCols      = refCols;
        copy.refIndex     = refIndex;
        copy.deleteAction = deleteAction;
        copy.updateAction = updateAction;
        copy.matchType    = matchType;

        return copy;
    }
