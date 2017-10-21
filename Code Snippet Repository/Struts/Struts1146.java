    public void testFindConversionMappingForInterface() {
        ModelDrivenAnnotationAction2 action = new ModelDrivenAnnotationAction2();
        ValueStack stack = ActionContext.getContext().getValueStack();
        stack.push(action);
        stack.push(action.getModel());

        Map<String, Object> ognlStackContext = stack.getContext();
        ognlStackContext.put(XWorkConverter.REPORT_CONVERSION_ERRORS, Boolean.TRUE);

        String value = "asdf:123";
        Object o = converter.convertValue(ognlStackContext, action.getModel(), null, "barObj", value, Bar.class);
        assertNotNull(o);
        assertTrue("class is: " + o.getClass(), o instanceof Bar);

        Bar b = (Bar) o;
        assertEquals(value, b.getTitle() + ":" + b.getSomethingElse());
    }
