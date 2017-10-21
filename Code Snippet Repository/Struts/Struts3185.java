    public ValueStackDataSource(ValueStack valueStack, String dataSourceParam, boolean wrapField) {
        this.valueStack = valueStack;
        this.dataSource = dataSourceParam;
        this.wrapField = wrapField;

        Object dataSourceValue = valueStack.findValue(dataSource);

        if (dataSourceValue != null) {
            if (MakeIterator.isIterable(dataSourceValue)) {
                iterator = MakeIterator.convert(dataSourceValue);
            } else {
                Object[] array = new Object[1];
                array[0] = dataSourceValue;
                iterator = MakeIterator.convert(array);
            }
        } else {
            if (LOG.isWarnEnabled()) {
                LOG.warn("Data source value for data source " + dataSource + " was null");
            }
        }
    }
