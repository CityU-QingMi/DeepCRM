    public void testTwoExcludesPropertiesChained() throws Exception {
        TestBean bean = new TestBean();
        TestBeanAction action = new TestBeanAction();
        mockInvocation.matchAndReturn("getAction", action);
        bean.setBirth(new Date());
        bean.setName("foo");
        bean.setCount(1);
        stack.push(bean);
        stack.push(action);

        String excludes = "name,count";
        interceptor.setExcludes(excludes);
        interceptor.intercept(invocation);
        assertEquals(bean.getBirth(), action.getBirth());
        assertEquals(null, action.getName());
        assertEquals(0, action.getCount());
        assertTrue(interceptor.getExcludes().contains("count"));
        assertTrue(interceptor.getExcludes().contains("name"));
        assertTrue(interceptor.getExcludes().size() == 2);
    }
