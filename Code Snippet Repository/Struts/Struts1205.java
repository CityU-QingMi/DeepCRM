    public void testExcludesPropertiesChained() throws Exception {
        TestBean bean = new TestBean();
        TestBeanAction action = new TestBeanAction();
        mockInvocation.matchAndReturn("getAction", action);
        bean.setBirth(new Date());
        bean.setName("foo");
        bean.setCount(1);
        stack.push(bean);
        stack.push(action);
        interceptor.setCopyErrors("true");
        interceptor.setCopyMessages("true");

        String excludes = "count";
        interceptor.setExcludes(excludes);

        interceptor.intercept(invocation);

        assertEquals(bean.getBirth(), action.getBirth());
        assertEquals(bean.getName(), action.getName());
        assertEquals(0, action.getCount());
        assertEquals(interceptor.getExcludes().iterator().next(), "count");
    }
