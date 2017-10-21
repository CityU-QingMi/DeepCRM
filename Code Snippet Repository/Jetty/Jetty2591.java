    @Before
    public void init() throws Exception
    {
        _handler=new RewriteHandler();
        _handler.setServer(_server);
        _handler.setHandler(new AbstractHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                response.setStatus(201);
                request.setAttribute("target",target);
                request.setAttribute("URI",request.getRequestURI());
                request.setAttribute("info",request.getPathInfo());
            }

        });
        _handler.start();

        _rule1 = new RewritePatternRule();
        _rule1.setPattern("/aaa/*");
        _rule1.setReplacement("/bbb");
        _rule2 = new RewritePatternRule();
        _rule2.setPattern("/bbb/*");
        _rule2.setReplacement("/ccc");
        _rule3 = new RewritePatternRule();
        _rule3.setPattern("/ccc/*");
        _rule3.setReplacement("/ddd");
        _rule4 = new RewriteRegexRule();
        _rule4.setRegex("/xxx/(.*)");
        _rule4.setReplacement("/$1/zzz");

        _handler.setRules(new Rule[]{_rule1,_rule2,_rule3,_rule4});

        start(false);
    }
