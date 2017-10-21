    private EmailValidator verifyCaseSensitive(final boolean caseSensitive) {
        ActionSupport action = new ActionSupport() {
            public boolean getEmailCaseSensitive() {
                return caseSensitive;
            }
        };

        EmailValidator validator = new EmailValidator();
        ValueStack valueStack = ActionContext.getContext().getValueStack();
        valueStack.push(action);
        validator.setValueStack(valueStack);

        validator.setCaseSensitiveExpression("${emailCaseSensitive}");

        return validator;
    }
