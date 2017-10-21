    private Profile newProfile( String key, String value )
    {
        ActivationProperty ap = new ActivationProperty();
        ap.setName( key );
        ap.setValue( value );

        Activation a = new Activation();
        a.setProperty( ap );

        Profile p = new Profile();
        p.setActivation( a );

        return p;
    }
