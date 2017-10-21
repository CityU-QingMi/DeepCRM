    private static boolean objectsEqual( Object o1, Object o2 )
    {
        if ( o1 == null && o2 == null )
        {
            return true;
        }
        if ( o1 == null || o2 == null )
        {
            return false; // as they are not both null
        }
        return o1.equals( o2 );
    }
