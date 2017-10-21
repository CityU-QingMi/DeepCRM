    public void testTranslateVariables() throws Exception {
        stack.push(new Object() {
            public String getFoo() {
                return "bar";
            }
        });
        Object obj1 = strutsUtil.translateVariables("try: %{foo}");

        assertNotNull(obj1);
        assertTrue(obj1 instanceof String);
        assertEquals(obj1, "try: bar");
    }
