    private EmailValidator verifyTrim(final boolean trim) {
        ActionSupport action = new ActionSupport() {
            public boolean getTrimEmail() {
                return trim;
            }
        };

        EmailValidator validator = new EmailValidator();
        ValueStack valueStack = ActionContext.getContext().getValueStack();
        valueStack.push(action);
        validator.setValueStack(valueStack);

        validator.setTrimExpression("${trimEmail}");

        return validator;
    }
