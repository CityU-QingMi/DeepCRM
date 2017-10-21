    @Before
    public void init() throws Exception
    {
        this.testWebappDir = MavenTestingUtils.getProjectDirPath("src/test/webapp");
        Resource webapp = new PathResource(testWebappDir);
        
        _context = new WebAppContext();
        _context.setBaseResource(webapp);
        _context.setContextPath("/test");

        _loader = new WebAppClassLoader(_context);
        _loader.addJars(webapp.addPath("WEB-INF/lib"));
        _loader.addClassPath(webapp.addPath("WEB-INF/classes"));
        _loader.setName("test");
        
        _context.loadSystemClasses();
        _context.loadServerClasses();
    }
