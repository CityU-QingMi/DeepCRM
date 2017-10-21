    @Test
    public void testSessionActivation() throws Exception
    {
        ScopedInstance<WebSocketScopeContext> wsScopeBean = newInstance(WebSocketScopeContext.class);
        WebSocketScopeContext wsScope = wsScopeBean.instance;

        wsScope.create();
        try
        {
            // Scope 1
            wsScope.begin();
            BogusSession sess = new BogusSession("1");
            wsScope.setSession(sess);
            ScopedInstance<BogusSocket> sock1Bean = newInstance(BogusSocket.class);
            BogusSocket sock1 = sock1Bean.instance;
            assertThat("Socket 1 Session",sock1.getSession().toString(),is(sess.toString()));

            sock1Bean.destroy();
        }
        finally
        {
            wsScope.end();
        }

        wsScope.destroy();
        wsScopeBean.destroy();
    }
