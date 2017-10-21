    public boolean equals(Object other) {

        if (other == this) {
            return true;
        }

        if (other instanceof Type) {
            if (getClass() != other.getClass()) {
                return false;
            }

            return ((Type) other).typeCode == typeCode
                   && ((Type) other).precision == precision
                   && ((Type) other).scale == scale
                   && ((Type) other).userTypeModifier == userTypeModifier;
        }

        return false;
    }
