    public void setUp() throws Exception {
        mgr = new TemplateEngineManager();
        mockContainer = new Mock(Container.class);
        mockContainer.matchAndReturn("getInstance", C.args(C.eq(TemplateEngine.class), C.eq("jsp")), new JspTemplateEngine());
        mockContainer.matchAndReturn("getInstance", C.args(C.eq(TemplateEngine.class), C.eq("vm")), new VelocityTemplateEngine());
        mockContainer.matchAndReturn("getInstance", C.args(C.eq(TemplateEngine.class), C.eq("ftl")), new FreemarkerTemplateEngine());
        mockContainer.matchAndReturn("getInstanceNames", C.args(C.eq(TemplateEngine.class)), new HashSet() {{
            add("jsp");
            add("vm");
            add("ftl");
        }});
        
        mgr.setContainer((Container)mockContainer.proxy());
        mgr.setDefaultTemplateType("jsp");
    }
