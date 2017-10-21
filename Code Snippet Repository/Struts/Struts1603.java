    public void testValidUrlWithRegexExpression() throws Exception {
        URLValidator validator = new URLValidator();
        ActionContext.getContext().getValueStack().push(new MyAction());
        validator.setValueStack(ActionContext.getContext().getValueStack());
        validator.setUrlRegexExpression("${urlRegex}");

        Pattern pattern = Pattern.compile(validator.getUrlRegex());

        assertTrue(pattern.matcher("myapp://test.com").matches());
        assertFalse(pattern.matcher("myap://test.com").matches());
    }
