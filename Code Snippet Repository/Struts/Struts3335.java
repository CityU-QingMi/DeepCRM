    public void testJSONArray2() throws Exception {
        setRequestContent("json-12.txt");
        this.request.addHeader("Content-Type", "application/json");

        // interceptor
        JSONInterceptor interceptor = new JSONInterceptor();
        interceptor.setRoot("anotherBean.yetAnotherBean.beans");
        TestAction5 action = new TestAction5();

        this.invocation.setAction(action);
        this.invocation.getStack().push(action);

        interceptor.intercept(this.invocation);

        List<Bean> beans = action.getAnotherBean().getYetAnotherBean().getBeans();

        assertNotNull(beans);
        assertEquals(1, beans.size());
        assertTrue(beans.get(0).isBooleanField());
        assertEquals(beans.get(0).getStringField(), "test");
        assertEquals(beans.get(0).getIntField(), 10);
        assertEquals(beans.get(0).getCharField(), 's');
        assertEquals(beans.get(0).getDoubleField(), 10.1);
        assertEquals(beans.get(0).getByteField(), 3);
    }
