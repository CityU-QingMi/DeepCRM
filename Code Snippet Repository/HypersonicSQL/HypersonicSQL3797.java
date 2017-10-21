    public boolean canBeAssignedFrom(Type otherType) {

        if (otherType == null) {
            return true;
        }

        if (!otherType.isRowType()) {
            return false;
        }

        Type[] otherTypes = ((RowType) otherType).getTypesArray();

        if (dataTypes.length != otherTypes.length) {
            return false;
        }

        for (int i = 0; i < dataTypes.length; i++) {
            if (!dataTypes[i].canBeAssignedFrom(otherTypes[i])) {
                return false;
            }
        }

        return true;
    }
