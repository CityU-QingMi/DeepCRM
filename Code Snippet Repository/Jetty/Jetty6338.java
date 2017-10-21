    @Parameters
    public static Collection<Case[]> data()
    {
        List<Case[]> cases = new ArrayList<>();
        cases.add(new Case[]
        { new Case()
        {
            @Override
            public void customize(WebAppContext context)
            {
                // no customization
            }
        } });
        cases.add(new Case[]
        { new Case()
        {
            @Override
            public void customize(WebAppContext context)
            {
                // Test with DefaultServlet only
                context.addServlet(DefaultServlet.class,"/");
            }
        } });
        cases.add(new Case[]
        { new Case()
        {
            @Override
            public void customize(WebAppContext context)
            {
                // Test with Servlet mapped to "/*"
                context.addServlet(DefaultServlet.class,"/*");
            }
        } });
        cases.add(new Case[]
        { new Case()
        {
            @Override
            public void customize(WebAppContext context)
            {
                // Test with Servlet mapped to "/info/*"
                context.addServlet(DefaultServlet.class,"/info/*");
            }
        } });
        return cases;
    }
