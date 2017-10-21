    public boolean equals(Object other) {

        if (other == this) {
            return true;
        }

        if (other instanceof ArrayType) {
            return super.equals(other)
                   && maxCardinality == ((ArrayType) other).maxCardinality
                   && dataType.equals(((ArrayType) other).dataType);
        }

        return false;
    }
