    public void testGenericPropertiesFromSetter() {
        GenericsBean gb = new GenericsBean();
        ValueStack stack = ac.getValueStack();
        stack.push(gb);

        stack.setValue("genericMap[123.12]", "66");
        stack.setValue("genericMap[456.12]", "42");

        assertEquals(2, gb.getGenericMap().size());
        assertEquals("66", stack.findValue("genericMap.get(123.12).toString()"));
        assertEquals("42", stack.findValue("genericMap.get(456.12).toString()"));
        assertEquals(66, stack.findValue("genericMap.get(123.12)"));
        assertEquals(42, stack.findValue("genericMap.get(456.12)"));
        assertEquals(true, stack.findValue("genericMap.containsValue(66)"));
        assertEquals(true, stack.findValue("genericMap.containsValue(42)"));
        assertEquals(true, stack.findValue("genericMap.containsKey(123.12)"));
        assertEquals(true, stack.findValue("genericMap.containsKey(456.12)"));
    }
