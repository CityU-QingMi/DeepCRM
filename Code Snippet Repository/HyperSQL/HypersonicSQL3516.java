    public boolean canConvertFrom(Type otherType) {

        if (otherType == null) {
            return true;
        }

        if (!otherType.isArrayType()) {
            return false;
        }

        Type otherComponent = otherType.collectionBaseType();

        return dataType.canConvertFrom(otherComponent);
    }
