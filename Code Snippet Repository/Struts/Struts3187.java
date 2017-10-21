    public void moveFirst() throws JRException {
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
            LOG.warn("Data source value for data source [{}] was null", dataSource);
        }
    }
