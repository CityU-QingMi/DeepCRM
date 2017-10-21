    @Override
    public void setProperty(Map context, Object target, Object name, Object value) throws OgnlException {
        Class lastClass = (Class) context.get(XWorkConverter.LAST_BEAN_CLASS_ACCESSED);
        String lastProperty = (String) context.get(XWorkConverter.LAST_BEAN_PROPERTY_ACCESSED);
        Class convertToClass = objectTypeDeterminer.getElementClass(lastClass, lastProperty, name);

        if (name instanceof String && value.getClass().isArray()) {
            // looks like the input game in the form of "someCollection.foo" and
            // we are expected to define the index values ourselves.
            // So let's do it:

            Collection c = (Collection) target;
            Object[] values = (Object[]) value;
            for (Object v : values) {
                try {
                    Object o = objectFactory.buildBean(convertToClass, context);
                    ognlUtil.setValue((String) name, context, o, v);
                    c.add(o);
                } catch (Exception e) {
                    throw new OgnlException("Error converting given String values for Collection.", e);
                }
            }

            // we don't want to do the normal collection property setting now, since we've already done the work
            // just return instead
            return;
        }

        Object realValue = getRealValue(context, value, convertToClass);

        super.setProperty(context, target, name, realValue);
    }
