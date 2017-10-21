    public boolean canBeAssignedFrom(Type otherType) {

        if (otherType == null) {
            return true;
        }

        Type otherComponent = otherType.collectionBaseType();

        return otherComponent != null
               && dataType.canBeAssignedFrom(otherComponent);
    }
