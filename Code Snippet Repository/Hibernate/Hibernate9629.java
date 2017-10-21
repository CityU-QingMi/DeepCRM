    public boolean equals( Object obj ) {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        final PersonID other = (PersonID) obj;
        if ( name == null ) {
            if ( other.name != null )
                return false;

        } else if ( !name.equals( other.name ) ) {
            return false;
        }
        if ( num == null ) {
            if ( other.num != null )
                return false;

        } else if ( !num.equals( other.num ) ) {
            return false;
        }
        return true;
    }
