    public void test2WithEnumBean() throws Exception {
        JSONResult result = new JSONResult();
        result.setEnumAsBean(true);

        executeTest2Action(result);

        String json = response.getContentAsString();

        String normalizedActual = TestUtils.normalize(json, true);
        String normalizedExpected = TestUtils.normalize(JSONResultTest.class.getResource("json-2-enum.txt"));
        assertEquals(normalizedExpected, normalizedActual);
        assertEquals("application/json;charset=UTF-8", response.getContentType());
    }
