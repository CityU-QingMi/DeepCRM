    public void testIncludePropertiesWithSetList() throws Exception {
        JSONResult result = new JSONResult();
        result.setIncludeProperties("^set\\[\\d+\\]\\.list\\[\\d+\\]\\.booleanField");
        TestAction action = new TestAction();
        stack.push(action);

        Set set = new LinkedHashSet();

        TestAction a1 = new TestAction();

        List list = new ArrayList();

        list.add(new Bean());
        list.add(new Bean());
        list.add(new Bean());

        a1.setList(list);
        set.add(a1);

        TestAction a2 = new TestAction();

        list = new ArrayList();

        list.add(new Bean());
        list.add(new Bean());

        a2.setList(list);
        set.add(a2);

        action.setSet(set);

        this.invocation.setAction(action);
        result.execute(this.invocation);

        String json = response.getContentAsString();
        String normalizedActual = TestUtils.normalize(json, true);
        String normalizedExpected = TestUtils.normalize(JSONResultTest.class.getResource("json-11.txt"));
        assertEquals(normalizedExpected, normalizedActual);
        assertEquals("application/json;charset=UTF-8", response.getContentType());
    }
