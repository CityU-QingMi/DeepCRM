    public boolean equals(Expression other) {

        if (other == this) {
            return true;
        }

        if (other == null) {
            return false;
        }

        if (opType != other.opType) {
            return false;
        }

        switch (opType) {

            case OpTypes.SIMPLE_COLUMN :
                return this.columnIndex == other.columnIndex
                       && rangeVariable
                          == ((ExpressionColumn) other).rangeVariable;

            case OpTypes.COALESCE :
                return nodes == other.nodes;

            case OpTypes.DYNAMIC_PARAM :
            case OpTypes.VARIABLE :
            case OpTypes.PARAMETER :
            case OpTypes.COLUMN :
                return column == other.getColumn()
                       && rangeVariable == other.getRangeVariable();

            default :
                return false;
        }
    }
