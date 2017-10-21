    public void testIncludePropertiesWithList() throws Exception {
        JSONResult result = new JSONResult();
        result.setIncludeProperties("^list\\[\\d+\\]\\.booleanField");
        TestAction action = new TestAction();
        stack.push(action);

        List list = new ArrayList();

        list.add(new Bean());
        list.add(new Bean());
        list.add(new Bean());

        action.setList(list);

        this.invocation.setAction(action);
        result.execute(this.invocation);

        String json = response.getContentAsString();
        String normalizedActual = TestUtils.normalize(json, true);
        String normalizedExpected = TestUtils.normalize(JSONResultTest.class.getResource("json-10.txt"));
        assertEquals(normalizedExpected, normalizedActual);
        assertEquals("application/json;charset=UTF-8", response.getContentType());
    }
