    public void testGenericProperties() {
        GenericsBean gb = new GenericsBean();
        ValueStack stack = ac.getValueStack();
        stack.push(gb);

        String[] value = new String[] {"123.12", "123.45"};
        stack.setValue("doubles", value);
        assertEquals(2, gb.getDoubles().size());
        assertEquals(Double.class, gb.getDoubles().get(0).getClass());
        assertEquals(new Double(123.12), gb.getDoubles().get(0));
        assertEquals(new Double(123.45), gb.getDoubles().get(1));
    }
