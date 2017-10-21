    @Test
    public void testMultiSession_Sequential() throws Exception
    {
        ScopedInstance<WebSocketScopeContext> wsScope1Bean = newInstance(WebSocketScopeContext.class);
        WebSocketScopeContext wsScope1 = wsScope1Bean.instance;
        
        ScopedInstance<WebSocketScopeContext> wsScope2Bean = newInstance(WebSocketScopeContext.class);
        WebSocketScopeContext wsScope2 = wsScope2Bean.instance;

        wsScope1.create();
        try
        {
            // Scope 1
            wsScope1.begin();
            BogusSession sess = new BogusSession("1");
            wsScope1.setSession(sess);
            ScopedInstance<BogusSocket> sock1Bean = newInstance(BogusSocket.class);
            BogusSocket sock1 = sock1Bean.instance;
            assertThat("Socket 1 Session",sock1.getSession(),sameInstance((Session)sess));
            sock1Bean.destroy();
        }
        finally
        {
            wsScope1.end();
        }
        
        wsScope1.destroy();
        wsScope1Bean.destroy();

        wsScope2.create();
        try
        {
            // Scope 2
            wsScope2.begin();
            BogusSession sess = new BogusSession("2");
            wsScope2.setSession(sess);
            ScopedInstance<BogusSocket> sock2Bean = newInstance(BogusSocket.class);
            BogusSocket sock2 = sock2Bean.instance;
            assertThat("Socket 2 Session",sock2.getSession(),sameInstance((Session)sess));
            sock2Bean.destroy();
        }
        finally
        {
            wsScope2.end();
        }

        wsScope2.destroy();
        wsScope2Bean.destroy();
    }
