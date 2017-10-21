    private boolean compareObjects( Object first, Object second )
    {
        if ( first == second )
        {
            return true;
        }

        if ( first == null || second == null )
        {
            return false;
        }

        return first.equals( second );
    }
