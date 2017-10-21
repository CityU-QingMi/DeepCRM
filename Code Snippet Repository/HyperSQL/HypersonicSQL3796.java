    public boolean canConvertFrom(Type otherType) {

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
            if (!dataTypes[i].canConvertFrom(otherTypes[i])) {
                return false;
            }
        }

        return true;
    }
