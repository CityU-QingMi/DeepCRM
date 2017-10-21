    public Constraint(HsqlName name, HsqlName refTableName,
                      OrderedHashSet refCols, HsqlName mainTableName,
                      OrderedHashSet mainCols, int type, int deleteAction,
                      int updateAction, int matchType) {

        this.name          = name;
        constType          = type;
        mainColSet         = mainCols;
        refColSet          = refCols;
        core               = new ConstraintCore();
        core.refTableName  = refTableName;
        core.mainTableName = mainTableName;
        core.deleteAction  = deleteAction;
        core.updateAction  = updateAction;
        core.matchType     = matchType;

        switch (core.deleteAction) {

            case SchemaObject.ReferentialAction.CASCADE :
            case SchemaObject.ReferentialAction.SET_DEFAULT :
            case SchemaObject.ReferentialAction.SET_NULL :
                core.hasDeleteAction = true;
                break;

            default :
        }

        switch (core.updateAction) {

            case SchemaObject.ReferentialAction.CASCADE :
            case SchemaObject.ReferentialAction.SET_DEFAULT :
            case SchemaObject.ReferentialAction.SET_NULL :
                core.hasUpdateAction = true;
                break;

            default :
        }
    }
