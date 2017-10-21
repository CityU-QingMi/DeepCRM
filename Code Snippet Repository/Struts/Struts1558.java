    public boolean verifyEmailValidityWithExpression(final String email, final String expression) throws Exception {
        ActionSupport action = new ActionSupport() {
            public String getMyEmail() {
                return email;
            }

            public String getEmailExpression() {
                return expression;
            }
        };

        EmailValidator validator = new EmailValidator();
        ValueStack valueStack = ActionContext.getContext().getValueStack();
        valueStack.push(action);
        validator.setValueStack(valueStack);

        validator.setValidatorContext(new DelegatingValidatorContext(action, tpf));
        validator.setFieldName("myEmail");
        validator.setDefaultMessage("invalid email");
        validator.setRegexExpression("${emailExpression}");

        validator.validate(action);

        return (action.getFieldErrors().size() == 0);
    }
