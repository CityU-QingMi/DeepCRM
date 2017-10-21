    public void testMng5568()
    {
        String a = "6.1.0";
        String b = "6.1.0rc3";
        String c = "6.1H.5-beta"; // this is the unusual version string, with 'H' in the middle

        checkVersionsOrder( b, a ); // classical
        checkVersionsOrder( b, c ); // now b < c, but before MNG-5568, we had b > c
        checkVersionsOrder( a, c );
    }
