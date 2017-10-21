    public void testFindStringMethod() throws Exception {
        stack.push(new Object() {
            public String getMyString() {
                return "myString";
            }
            public boolean getMyBoolean(boolean bool) {
                return bool;
            }
        });

        assertEquals(strutsUtil.findString("myString"), "myString");
        assertNull(strutsUtil.findString("myOtherString"));
        assertEquals(strutsUtil.findString("getMyBoolean(true)"), "true");
    }
