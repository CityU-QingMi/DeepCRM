    public void testShouldOverrideAndActivateInactiveProfile()
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

        profileManager.explicitlyActivate( "syspropActivated" );

        List active = profileManager.getActiveProfiles();

        assertNotNull( active );
        assertEquals( 1, active.size() );
        assertEquals( "syspropActivated", ( (Profile) active.get( 0 ) ).getId() );
    }
