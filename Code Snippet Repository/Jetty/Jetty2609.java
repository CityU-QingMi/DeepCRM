    @Test
    public void testCascadingRules() throws Exception
    {
        _request.setAuthority("foo.com",0);
        _request.setURIPathQuery("/cheese/bar");

        _rule.setTerminating(false);
        _fooRule.setTerminating(false);
        _fooContainerRule.setTerminating(false);

        _handler.setRules(new Rule[]{_rule, _fooContainerRule});
        handleRequest();
        assertEquals("{_rule, _fooContainerRule}: applied _rule, didn't match _fooRule", "/rule/bar", _request.getRequestURI());

        _request.setURIPathQuery("/cheese/bar");
        _handler.setRules(new Rule[] { _fooContainerRule, _rule });
        handleRequest();
        assertEquals("{_fooContainerRule, _rule}: applied _fooRule, _rule","/rule/fooRule", _request.getRequestURI());

        _request.setURIPathQuery("/cheese/bar");
        _fooRule.setTerminating(true);
        handleRequest();
        assertEquals("{_fooContainerRule, _rule}: (_fooRule is terminating); applied _fooRule, _rule", "/rule/fooRule", _request.getRequestURI());

        _request.setURIPathQuery("/cheese/bar");
        _fooRule.setTerminating(false);
        _fooContainerRule.setTerminating(true);
        handleRequest();
        assertEquals("{_fooContainerRule, _rule}: (_fooContainerRule is terminating); applied _fooRule, terminated before _rule", "/cheese/fooRule", _request.getRequestURI());
    }
