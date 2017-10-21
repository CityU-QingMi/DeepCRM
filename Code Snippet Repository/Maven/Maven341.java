    public void testShouldActivateDefaultProfile()
        throws Exception
    {
        Profile notActivated = new Profile();
        notActivated.setId( "notActivated" );

        Activation nonActivation = new Activation();

        nonActivation.setJdk( "19.2" );

        notActivated.setActivation( nonActivation );

        Profile defaultActivated = new Profile();
        defaultActivated.setId( "defaultActivated" );

        Activation defaultActivation = new Activation();

        defaultActivation.setActiveByDefault( true );

        defaultActivated.setActivation( defaultActivation );

        Properties props = System.getProperties();

        ProfileManager profileManager = new DefaultProfileManager( getContainer(), props );

        profileManager.addProfile( notActivated );
        profileManager.addProfile( defaultActivated );

        List active = profileManager.getActiveProfiles();

        assertNotNull( active );
        assertEquals( 1, active.size() );
        assertEquals( "defaultActivated", ( (Profile) active.get( 0 ) ).getId() );
    }
