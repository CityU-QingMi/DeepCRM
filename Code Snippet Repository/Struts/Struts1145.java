    public void testFindConversionErrorMessage() {
        ModelDrivenAnnotationAction action = new ModelDrivenAnnotationAction();
        container.inject(action);

        ValueStack stack = ActionContext.getContext().getValueStack();
        stack.push(action);
        stack.push(action.getModel());

        String message = XWorkConverter.getConversionErrorMessage("birth", stack);
        assertNotNull(message);
        assertEquals("Invalid date for birth.", message);

        message = XWorkConverter.getConversionErrorMessage("foo", stack);
        assertNotNull(message);
        assertEquals("Invalid field value for field \"foo\".", message);
    }
