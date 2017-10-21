    public Constraint(HsqlName name, Table t, Index index, int type) {

        this.name      = name;
        constType      = type;
        core           = new ConstraintCore();
        core.mainTable = t;
        core.mainIndex = index;
        core.mainCols  = index.getColumns();

        for (int i = 0; i < core.mainCols.length; i++) {
            Type dataType = t.getColumn(core.mainCols[i]).getDataType();

            if (dataType.isLobType()) {
                throw Error.error(ErrorCode.X_42534);
            }
        }
    }
