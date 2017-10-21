    public void testRoot() throws Exception {
        setRequestContent("json-5.txt");
        this.request.addHeader("Content-Type", "application/json");

        // interceptor
        JSONInterceptor interceptor = new JSONInterceptor();
        interceptor.setRoot("bean");
        TestAction4 action = new TestAction4();

        this.invocation.setAction(action);
        this.invocation.getStack().push(action);

        interceptor.intercept(this.invocation);

        Bean bean2 = action.getBean();

        assertNotNull(bean2);
        assertTrue(bean2.isBooleanField());
        assertEquals(bean2.getStringField(), "test");
        assertEquals(bean2.getIntField(), 10);
        assertEquals(bean2.getCharField(), 's');
        assertEquals(bean2.getDoubleField(), 10.1);
        assertEquals(bean2.getByteField(), 3);
    }
