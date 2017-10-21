    SimpleName getSimpleName() {

        if (alias != null) {
            return alias;
        }

        if (rangeVariable != null && rangeVariable.hasColumnAlias()) {
            return rangeVariable.getColumnAlias(columnIndex);
        }

        if (column != null) {
            return column.getName();
        }

        if (opType == OpTypes.COALESCE) {
            return nodes[LEFT].getSimpleName();
        } else if (opType == OpTypes.ROWNUM) {
            return rownumName;
        }

        return null;
    }
