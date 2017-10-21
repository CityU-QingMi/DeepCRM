    public void testVersionParsing()
    {
        checkVersionParsing( "1", 1, 0, 0, 0, null );
        checkVersionParsing( "1.2", 1, 2, 0, 0, null );
        checkVersionParsing( "1.2.3", 1, 2, 3, 0, null );
        checkVersionParsing( "1.2.3-1", 1, 2, 3, 1, null );
        checkVersionParsing( "1.2.3-alpha-1", 1, 2, 3, 0, "alpha-1" );
        checkVersionParsing( "1.2-alpha-1", 1, 2, 0, 0, "alpha-1" );
        checkVersionParsing( "1.2-alpha-1-20050205.060708-1", 1, 2, 0, 0, "alpha-1-20050205.060708-1" );
        checkVersionParsing( "RELEASE", 0, 0, 0, 0, "RELEASE" );
        checkVersionParsing( "2.0-1", 2, 0, 0, 1, null );

        // 0 at the beginning of a number has a special handling
        checkVersionParsing( "02", 0, 0, 0, 0, "02" );
        checkVersionParsing( "0.09", 0, 0, 0, 0, "0.09" );
        checkVersionParsing( "0.2.09", 0, 0, 0, 0, "0.2.09" );
        checkVersionParsing( "2.0-01", 2, 0, 0, 0, "01" );

        // version schemes not really supported: fully transformed as qualifier
        checkVersionParsing( "1.0.1b", 0, 0, 0, 0, "1.0.1b" );
        checkVersionParsing( "1.0M2", 0, 0, 0, 0, "1.0M2" );
        checkVersionParsing( "1.0RC2", 0, 0, 0, 0, "1.0RC2" );
        checkVersionParsing( "1.1.2.beta1", 1, 1, 2, 0, "beta1" );
        checkVersionParsing( "1.7.3.beta1", 1, 7, 3, 0, "beta1" );
        checkVersionParsing( "1.7.3.0", 0, 0, 0, 0, "1.7.3.0" );
        checkVersionParsing( "1.7.3.0-1", 0, 0, 0, 0, "1.7.3.0-1" );
        checkVersionParsing( "PATCH-1193602", 0, 0, 0, 0, "PATCH-1193602" );
        checkVersionParsing( "5.0.0alpha-2006020117", 0, 0, 0, 0, "5.0.0alpha-2006020117" );
        checkVersionParsing( "1.0.0.-SNAPSHOT", 0, 0, 0, 0, "1.0.0.-SNAPSHOT" );
        checkVersionParsing( "1..0-SNAPSHOT", 0, 0, 0, 0, "1..0-SNAPSHOT" );
        checkVersionParsing( "1.0.-SNAPSHOT", 0, 0, 0, 0, "1.0.-SNAPSHOT" );
        checkVersionParsing( ".1.0-SNAPSHOT", 0, 0, 0, 0, ".1.0-SNAPSHOT" );

        checkVersionParsing( "1.2.3.200705301630", 0, 0, 0, 0, "1.2.3.200705301630" );
        checkVersionParsing( "1.2.3-200705301630", 1, 2, 3, 0, "200705301630" );
    }
