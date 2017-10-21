    public void testProxyAccessIsBlocked() throws Exception {
        ActionProxy proxy = actionProxyFactory.createActionProxy(null,
                "chaintoAOPedTestSubBeanAction", null, context);

        SecurityMemberAccess sma = new SecurityMemberAccess(false);
        sma.setDisallowProxyMemberAccess(true);

        Member member = proxy.getAction().getClass().getMethod("isExposeProxy");

        boolean accessible = sma.isAccessible(context, proxy.getAction(), member, "");
        assertFalse(accessible);
    }
