    private IntRangeFieldValidator prepareValidator(ValidationAction action, ValidatorContext context) {
        ValueStack valueStack = container.getInstance(ValueStackFactory.class).createValueStack();
        valueStack.push(action);

        IntRangeFieldValidator validator = new IntRangeFieldValidator();
        validator.setValueStack(valueStack);

        validator.setMaxExpression("${intMaxValue}");
        validator.setMinExpression("${intMinValue}");
        validator.setValidatorContext(context);
        validator.setFieldName("intRange");
        validator.setDefaultMessage("Max is ${intMaxValue}, min is ${intMinValue} but value is ${intRange}");

        return validator;
    }
