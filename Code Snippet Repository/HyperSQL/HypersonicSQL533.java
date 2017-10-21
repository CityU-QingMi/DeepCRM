    public TableDerived getValueListTable() {

        if (isValueList) {
            RangeVariable range = null;

            if (rangeVariables == null) {
                if (rangeVariableList.size() == 1) {
                    range = (RangeVariable) rangeVariableList.get(0);
                }
            } else if (rangeVariables.length == 1) {
                range = rangeVariables[0];
            }

            if (range != null) {
                return (TableDerived) range.getTable();
            }
        }

        return null;
    }
