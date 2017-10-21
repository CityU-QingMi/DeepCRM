    @Before
    public void init() throws Exception
    {
        _handler = new RewriteHandler();
        _handler.setRewriteRequestURI(true);

        _rule = new RewritePatternRule();
        _rule.setPattern("/cheese/*");
        _rule.setReplacement("/rule");

        _fooRule = new RewritePatternRule();
        _fooRule.setPattern("/cheese/bar/*");
        _fooRule.setReplacement("/cheese/fooRule");

        _fooContainerRule = new VirtualHostRuleContainer();
        _fooContainerRule.setVirtualHosts(new String[] {"foo.com"});
        _fooContainerRule.setRules(new Rule[] { _fooRule });

        start(false);
        _request.setURIPathQuery("/cheese/bar");
        
        _handler.setServer(_server);
        _handler.start();
    }
