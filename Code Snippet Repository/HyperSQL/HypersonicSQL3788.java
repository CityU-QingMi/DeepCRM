    public boolean equals(Object other) {

        if (other == this) {
            return true;
        }

        if (other instanceof Type) {
            if (((Type) other).typeCode != Types.SQL_ROW) {
                return false;
            }

            Type[] otherTypes = ((RowType) other).dataTypes;

            if (otherTypes.length != dataTypes.length) {
                return false;
            }

            for (int i = 0; i < dataTypes.length; i++) {
                if (!dataTypes[i].equals(otherTypes[i])) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }
