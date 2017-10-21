    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        if ( name != null ) {
            result += name.hashCode();
        }
        result *= PRIME;
        if ( num != null ) {
            result += num.hashCode();
        }
        return result;
    }
