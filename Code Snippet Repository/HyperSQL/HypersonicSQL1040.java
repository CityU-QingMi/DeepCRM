    void checkReferentialColumnsMatch(int[] col, Table other, int[] othercol) {

        for (int i = 0; i < col.length; i++) {
            Type type      = colTypes[col[i]];
            Type otherType = other.colTypes[othercol[i]];

            if (!type.canCompareDirect(otherType)) {
                throw Error.error(ErrorCode.X_42562);
            }
        }
    }
