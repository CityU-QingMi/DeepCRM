    public void testIsTrueMethod() throws Exception {
        stack.push(new Object() {
            public String getMyString() {
                return "myString";
            }
            public boolean getMyBoolean(boolean bool) {
                return bool;
            }
        });
        assertTrue(strutsUtil.isTrue("myString == 'myString'"));
        assertFalse(strutsUtil.isTrue("myString == 'myOtherString'"));
        assertTrue(strutsUtil.isTrue("getMyBoolean(true)"));
        assertFalse(strutsUtil.isTrue("getMyBoolean(false)"));
    }
