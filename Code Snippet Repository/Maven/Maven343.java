    public void testShouldNotActivateReversalOfPresentSystemProperty()
        throws Exception
    {
        Profile syspropActivated = new Profile();
        syspropActivated.setId( "syspropActivated" );

        Activation syspropActivation = new Activation();

        ActivationProperty syspropProperty = new ActivationProperty();
        syspropProperty.setName( "!java.version" );

        syspropActivation.setProperty( syspropProperty );

        syspropActivated.setActivation( syspropActivation );

        Properties props = System.getProperties();

        ProfileManager profileManager = new DefaultProfileManager( getContainer(), props );

        profileManager.addProfile( syspropActivated );

        List active = profileManager.getActiveProfiles();

        assertNotNull( active );
        assertEquals( 0, active.size() );
    }
