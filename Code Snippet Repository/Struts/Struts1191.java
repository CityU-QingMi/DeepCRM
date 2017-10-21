    public void testFindConversionErrorMessage() {
        ModelDrivenAction action = new ModelDrivenAction();
        container.inject(action);

        stack.push(action);
        stack.push(action.getModel());

        String message = XWorkConverter.getConversionErrorMessage("birth", stack);
        assertNotNull(message);
        assertEquals("Invalid date for birth.", message);

        message = XWorkConverter.getConversionErrorMessage("foo", stack);
        assertNotNull(message);
        assertEquals("Invalid field value for field \"foo\".", message);
    }
