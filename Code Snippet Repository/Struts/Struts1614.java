    public void testModelFieldErrorsAddedWithoutFieldPrefix() throws Exception {
        container.getInstance(ActionValidatorManager.class).validate(action, null);
        assertTrue(action.hasFieldErrors());

        Map<String, List<String>> fieldErrors = action.getFieldErrors();

        // the required string validation inherited from the VisitorValidatorTestAction
        assertTrue(fieldErrors.containsKey("context"));

        // the bean validation which is now at the top level because we set the appendPrefix to false
        assertTrue(fieldErrors.containsKey("name"));

        List<String> nameMessages = fieldErrors.get("name");
        assertEquals(1, nameMessages.size());

        String nameMessage = (String) nameMessages.get(0);
        assertEquals("You must enter a name.", nameMessage);
    }
