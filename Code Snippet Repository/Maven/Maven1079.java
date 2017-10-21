    public static DefaultLifecycles createDefaultLifecycles()
    {

        List<String> stubDefaultCycle =
            Arrays.asList( VALIDATE.getPhase(), INITIALIZE.getPhase(), PROCESS_RESOURCES.getPhase(), COMPILE.getPhase(),
                           TEST.getPhase(), PROCESS_TEST_RESOURCES.getPhase(), PACKAGE.getPhase(), "BEER",
                           INSTALL.getPhase() );

        // The two phases below are really for future expansion, some would say they lack a drink
        // The point being that they do not really have to match the "real" stuff,
        List<String> stubCleanCycle = Arrays.asList( PRE_CLEAN.getPhase(), CLEAN.getPhase(), POST_CLEAN.getPhase() );

        List<String> stubSiteCycle =
            Arrays.asList( PRE_SITE.getPhase(), SITE.getPhase(), POST_SITE.getPhase(), SITE_DEPLOY.getPhase() );

        @SuppressWarnings( "unchecked" )
        Iterator<List<String>> lcs = Arrays.asList( stubDefaultCycle, stubCleanCycle, stubSiteCycle ).iterator();

        Map<String, Lifecycle> lifeCycles = new HashMap<>();
        for ( String s : DefaultLifecycles.STANDARD_LIFECYCLES )
        {
            final Lifecycle lifecycle = new Lifecycle( s, lcs.next(), null );
            lifeCycles.put( s, lifecycle );

        }
        return new DefaultLifecycles( lifeCycles, new LoggerStub() );
    }
