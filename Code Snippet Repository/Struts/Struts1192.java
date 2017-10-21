    public void testFindConversionMappingForInterface() {
        ModelDrivenAction2 action = new ModelDrivenAction2();
        stack.push(action);
        stack.push(action.getModel());

        Map<String, Object> ognlStackContext = stack.getContext();
        ognlStackContext.put(XWorkConverter.REPORT_CONVERSION_ERRORS, Boolean.TRUE);

        String value = "asdf:123";
        Object o = converter.convertValue(ognlStackContext, action.getModel(), null, "barObj", value, Bar.class);
        assertNotNull(o);
        assertTrue(o instanceof Bar);

        Bar b = (Bar) o;
        assertEquals(value, b.getTitle() + ":" + b.getSomethingElse());
    }
