    @SuppressWarnings("")
    public void testSMDObjectsNoResult() throws Exception {
        // request
        setRequestContent("smd-7.txt");
        this.request.addHeader("Content-Type", "application/json-rpc");

        JSONInterceptor interceptor = new JSONInterceptor();
        interceptor.setEnableSMD(true);
        SMDActionTest1 action = new SMDActionTest1();

        this.invocation.setAction(action);

        // can't be invoked
        interceptor.intercept(this.invocation);
        assertFalse(this.invocation.isInvoked());

        // asert values were passed properly
        Bean bean = action.getBeanParam();
        assertNotNull(bean);
        assertTrue(bean.isBooleanField());
        assertEquals(bean.getStringField(), "test");
        assertEquals(bean.getIntField(), 10);
        assertEquals(bean.getCharField(), 's');
        assertEquals(bean.getDoubleField(), 10.1);
        assertEquals(bean.getByteField(), 3);

        List list = action.getListParam();
        assertNotNull(list);
        assertEquals("str0", list.get(0));
        assertEquals("str1", list.get(1));

        Map map = action.getMapParam();
        assertNotNull(map);
        assertNotNull(map.get("a"));
        assertEquals(new Long(1), map.get("a"));
        assertNotNull(map.get("c"));
        List insideList = (List) map.get("c");
        assertEquals(1.0d, insideList.get(0));
        assertEquals(2.0d, insideList.get(1));

        String json = response.getContentAsString();
        String normalizedActual = TestUtils.normalize(json, true);
        String normalizedExpected = TestUtils.normalize(JSONResultTest.class.getResource("smd-11.txt"));
        assertEquals(normalizedExpected, normalizedActual);

        assertEquals("application/json;charset=UTF-8", response.getContentType());
    }
