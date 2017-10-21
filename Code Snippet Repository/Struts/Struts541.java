    private void validateObject(String fieldName, Object o, String visitorContext) throws ValidationException {
        ValueStack stack = ActionContext.getContext().getValueStack();
        stack.push(o);

        ValidatorContext validatorContext;

        if (appendPrefix) {
            ValidatorContext parent = getValidatorContext();
            validatorContext = new AppendingValidatorContext(parent, createTextProvider(o, parent), fieldName, getMessage(o));
        } else {
            ValidatorContext parent = getValidatorContext();
            CompositeTextProvider textProvider = createTextProvider(o, parent);
            validatorContext = new DelegatingValidatorContext(parent, textProvider, parent);
        }

        actionValidatorManager.validate(o, visitorContext, validatorContext);
        stack.pop();
    }
