    private Comparable newComparable( String version )
    {
        ComparableVersion ret = new ComparableVersion( version );
        String canonical = ret.getCanonical();
        String parsedCanonical = new ComparableVersion( canonical ).getCanonical();

        System.out.println( "canonical( " + version + " ) = " + canonical );
        assertEquals( "canonical( " + version + " ) = " + canonical + " -> canonical: " + parsedCanonical, canonical,
                      parsedCanonical );

        return ret;
    }
