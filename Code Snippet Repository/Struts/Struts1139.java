    public void testGenericPropertiesFromGetter() {
        GenericsBean gb = new GenericsBean();
        ValueStack stack = ac.getValueStack();
        stack.push(gb);

        assertEquals(1, gb.getGetterList().size());
        assertEquals("42.42", stack.findValue("getterList.get(0).toString()"));
        assertEquals(new Double(42.42), stack.findValue("getterList.get(0)"));
        assertEquals(new Double(42.42), gb.getGetterList().get(0));

    }
