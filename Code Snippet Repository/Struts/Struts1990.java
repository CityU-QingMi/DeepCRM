    public void testFindValueMethod() throws Exception {
        stack.push(new Object() {
            public String getMyString() {
                return "myString";
            }
            public boolean getMyBoolean(boolean bool) {
                return bool;
            }
        });
        Object obj1 = strutsUtil.findValue("myString", "java.lang.String");
        Object obj2 = strutsUtil.findValue("getMyBoolean(true)", "java.lang.Boolean");

        assertNotNull(obj1);
        assertNotNull(obj2);
        assertTrue(obj1 instanceof String);
        assertTrue(obj2 instanceof Boolean);
        assertEquals(obj1, "myString");
        assertEquals(obj2, Boolean.TRUE);
    }
