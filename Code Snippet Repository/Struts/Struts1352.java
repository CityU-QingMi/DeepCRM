    public void testFindValueWithConversion() {

        // register converter
        TestBean2 tb2 = new TestBean2();

        OgnlValueStack stack = createValueStack();
        stack.push(tb2);
        Map myContext = stack.getContext();

        Map props = new HashMap();
        props.put("cat", "Kitty");
        ognlUtil.setProperties(props, tb2, myContext);
        // expect String to be converted into a Cat
        assertEquals("Kitty", tb2.getCat().getName());

        // findValue should be able to access the name
        Object value = stack.findValue("cat.name == 'Kitty'", Boolean.class);
        assertNotNull(value);
        assertEquals(Boolean.class, value.getClass());
        assertEquals(Boolean.TRUE, value);

        value = stack.findValue("cat == null", Boolean.class);
        assertNotNull(value);
        assertEquals(Boolean.class, value.getClass());
        assertEquals(Boolean.FALSE, value);
    }
