    public void testTrimAsExpression() throws Exception {
        // given
        ValueStack valueStack = ActionContext.getContext().getValueStack();

        ActionSupport action = new ActionSupport() {
            public boolean getTrimValue() {
                return false;
            }
        };
        valueStack.push(action);

        RequiredStringValidator validator = new RequiredStringValidator();
        validator.setValueStack(valueStack);

        assertTrue(validator.isTrim());

        // when
        validator.setTrimExpression("${trimValue}");

        // then
        assertFalse(validator.isTrim());
    }
