    @Override
    protected void setUp()
        throws Exception
    {
        super.setUp();
        this.classRealmManager = lookup( ClassRealmManager.class );
        ClassLoader testRealm = getClass().getClassLoader();
        ServiceLoader<ScriptEngineFactory> sef = ServiceLoader.load( ScriptEngineFactory.class, testRealm );
        // TODO switch to Assume.assumeTrue( sef.iterator().hasNext() ) when PlexusTestCase
        // supports assumptions. Not every Java 7 JRE has a ScriptEngineFactory.
        this.haveScriptEngineFactory = sef.iterator().hasNext();
    }
