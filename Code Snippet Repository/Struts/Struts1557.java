    protected boolean verifyEmailValidity(final String email) throws Exception {
        ActionSupport action = new ActionSupport() {
            public String getMyEmail() {
                return email;
            }
        };

        EmailValidator validator = new EmailValidator();
        validator.setValidatorContext(new DelegatingValidatorContext(action, tpf));
        validator.setFieldName("myEmail");
        validator.setDefaultMessage("invalid email");
        validator.setValueStack(ActionContext.getContext().getValueStack());
        validator.validate(action);

        return (action.getFieldErrors().size() == 0);
    }
