    @Before
    public void init() throws Exception
    {
        server = new Server();
        wac = new WebAppContext();
        wac.setServer(server);
        injections = new InjectionCollection();
        wac.setAttribute(InjectionCollection.INJECTION_COLLECTION, injections);
        InitialContext ic = new InitialContext();
        comp = (Context)ic.lookup("java:comp");
        env = comp.createSubcontext("env");
    }
