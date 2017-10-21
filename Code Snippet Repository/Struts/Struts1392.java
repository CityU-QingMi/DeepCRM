    public void testProxyAccessIsAccessible() throws Exception {
        ActionProxy proxy = actionProxyFactory.createActionProxy(null,
                "chaintoAOPedTestSubBeanAction", null, context);

        SecurityMemberAccess sma = new SecurityMemberAccess(false);

        Member member = proxy.getAction().getClass().getMethod("isExposeProxy");

        boolean accessible = sma.isAccessible(context, proxy.getAction(), member, "");
        assertTrue(accessible);
    }
