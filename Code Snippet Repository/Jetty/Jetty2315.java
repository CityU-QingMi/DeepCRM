    @Before
    public void setUp() throws Exception
    {
        context = new WebAppContext();
        context.setClassLoader(new WebAppClassLoader(Thread.currentThread().getContextClassLoader(), context));
        ClassLoader oldLoader = Thread.currentThread().getContextClassLoader();
        Thread.currentThread().setContextClassLoader(context.getClassLoader());
        Context icontext = new InitialContext();
        Context compCtx =  (Context)icontext.lookup ("java:comp");
        compCtx.createSubcontext("env");
        Thread.currentThread().setContextClassLoader(oldLoader);

        org.eclipse.jetty.plus.jndi.Resource ds = new org.eclipse.jetty.plus.jndi.Resource (context, "jdbc/mydatasource", new Object());

        URL webXml = Thread.currentThread().getContextClassLoader().getResource("web.xml");
        webDescriptor = new WebDescriptor(org.eclipse.jetty.util.resource.Resource.newResource(webXml));
        webDescriptor.parse();

        URL frag1Xml = Thread.currentThread().getContextClassLoader().getResource("web-fragment-1.xml");
        fragDescriptor1 = new FragmentDescriptor(org.eclipse.jetty.util.resource.Resource.newResource(frag1Xml));
        fragDescriptor1.parse();
        URL frag2Xml = Thread.currentThread().getContextClassLoader().getResource("web-fragment-2.xml");
        fragDescriptor2 = new FragmentDescriptor(org.eclipse.jetty.util.resource.Resource.newResource(frag2Xml));
        fragDescriptor2.parse();
        URL frag3Xml = Thread.currentThread().getContextClassLoader().getResource("web-fragment-3.xml");
        fragDescriptor3 = new FragmentDescriptor(org.eclipse.jetty.util.resource.Resource.newResource(frag3Xml));
        fragDescriptor3.parse();
        URL frag4Xml = Thread.currentThread().getContextClassLoader().getResource("web-fragment-4.xml");
        fragDescriptor4 = new FragmentDescriptor(org.eclipse.jetty.util.resource.Resource.newResource(frag4Xml));
        fragDescriptor4.parse();
    }
