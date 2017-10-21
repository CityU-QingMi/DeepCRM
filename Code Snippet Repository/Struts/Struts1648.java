    private LongRangeFieldValidator prepareValidator(ValidationAction action, ValidatorContext context) {
        ValueStack valueStack = container.getInstance(ValueStackFactory.class).createValueStack();
        valueStack.push(action);

        LongRangeFieldValidator validator = new LongRangeFieldValidator();
        validator.setValueStack(valueStack);

        validator.setMaxExpression("${longMaxValue}");
        validator.setMinExpression("${longMinValue}");
        validator.setValidatorContext(context);
        validator.setFieldName("longRange");
        validator.setDefaultMessage("Max is ${longMaxValue}, min is ${longMinValue} but value is ${longRange}");

        return validator;
    }
