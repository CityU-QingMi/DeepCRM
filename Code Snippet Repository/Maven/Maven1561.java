    private Profile newProfile( String jdkVersion )
    {
        Activation a = new Activation();
        a.setJdk( jdkVersion );

        Profile p = new Profile();
        p.setActivation( a );

        return p;
    }
