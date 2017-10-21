    public Object getFieldValue(JRField field) throws JRException {
        //TODO: move the code to return a ValueStackDataSource to a seperate
        //      method when and if the JRDataSource interface is updated to support
        //      this.
        String expression = field.getName();

        Object value = valueStack.findValue(expression);
        LOG.debug("Field [{}] = [{}]", field.getName(), value);

        if (!wrapField && MakeIterator.isIterable(value) && field.getValueClass().isInstance(value)) {
            return value;
        } else if (MakeIterator.isIterable(value)) {
            // wrap value with ValueStackDataSource if not already wrapped
            return new ValueStackDataSource(this.valueStack, expression, wrapField);
        } else {
            return value;
        }
    }
