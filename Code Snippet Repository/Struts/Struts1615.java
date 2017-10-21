    public void testModelFieldErrorsAddedWithoutFieldPrefixForInterface() throws Exception {
        TestBean origBean = action.getBean();
        TestBean2 bean = new TestBean2();
        bean.setBirth(origBean.getBirth());
        bean.setCount(origBean.getCount());
        action.setBean(bean);
        assertTrue(action.getBean() instanceof TestBean2);

        container.getInstance(ActionValidatorManager.class).validate(action, null);
        assertTrue(action.hasFieldErrors());

        Map<String, List<String>> fieldErrors = action.getFieldErrors();

        // the required string validation inherited from the VisitorValidatorTestAction
        assertTrue(fieldErrors.containsKey("context"));

        // the bean validation which is now at the top level because we set the appendPrefix to false
        assertTrue(fieldErrors.containsKey("name"));

        List<String> nameMessages = fieldErrors.get("name");
        assertEquals(1, nameMessages.size());

        String nameMessage = nameMessages.get(0);
        assertEquals("You must enter a name.", nameMessage);

        // should also have picked up validation check for DataAware interface
        assertTrue(fieldErrors.containsKey("data"));

        List<String> dataMessages = fieldErrors.get("data");
        assertEquals(1, dataMessages.size());

        String dataMessage = dataMessages.get(0);
        assertEquals("You must enter a value for data.", dataMessage);
    }
