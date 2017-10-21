    public void testBeanMessagesUseBeanResourceBundle() throws Exception {
        validate("beanMessageBundle");
        assertTrue(action.hasFieldErrors());

        Map<String, List<String>> fieldErrors = action.getFieldErrors();
        assertTrue(fieldErrors.containsKey("bean.count"));

        List<String> beanCountMessages = fieldErrors.get("bean.count");
        assertEquals(1, beanCountMessages.size());

        String beanCountMessage = beanCountMessages.get(0);
        assertEquals("bean: TestBean model: Count must be between 1 and 100, current value is -1.", beanCountMessage);
    }
