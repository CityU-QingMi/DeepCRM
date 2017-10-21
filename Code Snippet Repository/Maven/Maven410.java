    public void testPerLookupInstantiation()
        throws Exception
    {
        String protocol = "perlookup";

        Wagon one = wagonManager.getWagon( protocol );
        Wagon two = wagonManager.getWagon( protocol );

        assertNotSame( one, two );
    }
