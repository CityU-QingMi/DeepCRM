    public boolean equals( Object obj ) {
        if ( obj == null )
            return false;
        if ( obj == this )
            return true;
        if ( !( obj instanceof Seller ) )
            return false;

        return ( (Seller) obj ).getId().equals( getId() );
    }
