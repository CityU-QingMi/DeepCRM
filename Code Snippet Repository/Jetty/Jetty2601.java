    @Before
    public void init() throws Exception
    {
        rewriteHandler = new RewriteHandler();
        rewriteHandler.setServer(_server);
        rewriteHandler.setHandler(new AbstractHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException,
                    ServletException
            {
                response.setStatus(HttpStatus.CREATED_201);
                request.setAttribute("target",target);
                request.setAttribute("URI",request.getRequestURI());
                request.setAttribute("info",request.getPathInfo());
            }
        });
        rewriteHandler.start();

        TerminatingRegexRule rule1 = new TerminatingRegexRule();
        rule1.setRegex("^/login.jsp$");
        rewriteHandler.addRule(rule1);
        RedirectRegexRule rule2 = new RedirectRegexRule();
        rule2.setRegex("^/login.*$");
        rule2.setReplacement("http://login.company.com/");
        rewriteHandler.addRule(rule2);

        start(false);
    }
