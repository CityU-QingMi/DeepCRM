    public void no_testGenericPropertiesWithNestedGenerics() {
        GenericsBean gb = new GenericsBean();
        ValueStack stack = ac.getValueStack();
        stack.push(gb);

        stack.setValue("extendedMap[123.12]", new String[] {"1", "2", "3", "4"});
        stack.setValue("extendedMap[456.12]", new String[] {"5", "6", "7", "8", "9"});

        System.out.println("gb.getExtendedMap(): " + gb.getExtendedMap());

        assertEquals(2, gb.getExtendedMap().size());
        System.out.println(stack.findValue("extendedMap"));
        assertEquals(4, stack.findValue("extendedMap.get(123.12).size"));
        assertEquals(5, stack.findValue("extendedMap.get(456.12).size"));

        assertEquals("1", stack.findValue("extendedMap.get(123.12).get(0)"));
        assertEquals("5", stack.findValue("extendedMap.get(456.12).get(0)"));
        assertEquals(Integer.class, stack.findValue("extendedMap.get(123.12).get(0).class"));
        assertEquals(Integer.class, stack.findValue("extendedMap.get(456.12).get(0).class"));

        assertEquals(List.class, stack.findValue("extendedMap.get(123.12).class"));
        assertEquals(List.class, stack.findValue("extendedMap.get(456.12).class"));

    }
