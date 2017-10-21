    public void testNullSafe()
        throws Exception
    {
        Profile p = new Profile();

        assertActivation( false, p, newContext( null, null ) );

        p.setActivation( new Activation() );

        assertActivation( false, p, newContext( null, null ) );
    }
