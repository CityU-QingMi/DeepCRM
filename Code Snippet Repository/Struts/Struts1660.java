    private ShortRangeFieldValidator prepareValidator(ValidationAction action, ValidatorContext context) {
        ValueStack valueStack = container.getInstance(ValueStackFactory.class).createValueStack();
        valueStack.push(action);

        ShortRangeFieldValidator validator = new ShortRangeFieldValidator();
        validator.setValueStack(valueStack);

        validator.setMaxExpression("${shortMaxValue}");
        validator.setMinExpression("${shortMinValue}");
        validator.setValidatorContext(context);
        validator.setFieldName("shortRange");
        validator.setDefaultMessage("Max is ${shortMaxValue}, min is ${shortMinValue} but value is ${shortRange}");

        return validator;
    }
