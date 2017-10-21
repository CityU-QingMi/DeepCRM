    public Type getCombinedType(Session session, Type other, int operation) {

        if (operation != OpTypes.CONCAT) {
            return getAggregateType(other);
        }

        if (other == null) {
            return this;
        }

        if (!other.isRowType()) {
            throw Error.error(ErrorCode.X_42562);
        }

        Type[] newTypes   = new Type[dataTypes.length];
        Type[] otherTypes = ((RowType) other).getTypesArray();

        if (dataTypes.length != otherTypes.length) {
            throw Error.error(ErrorCode.X_42564);
        }

        for (int i = 0; i < dataTypes.length; i++) {
            newTypes[i] = dataTypes[i].getAggregateType(otherTypes[i]);
        }

        return new RowType(newTypes);
    }
