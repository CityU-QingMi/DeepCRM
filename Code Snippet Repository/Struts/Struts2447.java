    public String execute() throws Exception {
        loadValidators();
        Validator validator = getSelectedValidator();
        properties = new TreeSet<PropertyInfo>();
        try {
            Map<String, Object> context = reflectionContextFactory.createDefaultContext(validator);
            BeanInfo beanInfoFrom;
            try {
                beanInfoFrom = Introspector.getBeanInfo(validator.getClass(), Object.class);
            } catch (IntrospectionException e) {
                LOG.error("An error occurred", e);
                addActionError("An error occurred while introspecting a validator of type " + validator.getClass().getName());
                return ERROR;
            }

            PropertyDescriptor[] pds = beanInfoFrom.getPropertyDescriptors();

            for (PropertyDescriptor pd : pds) {
                String name = pd.getName();
                Object value = null;
                if (pd.getReadMethod() == null) {
                    value = "No read method for property";
                } else {
                    try {
                        value = reflectionProvider.getValue(name, context, validator);
                    } catch (ReflectionException e) {
                        addActionError("Caught exception while getting property value for '" + name + "' on validator of type " + validator.getClass().getName());
                    }
                }
                properties.add(new PropertyInfo(name, pd.getPropertyType(), value));
            }
        } catch (Exception e) {
            if (LOG.isWarnEnabled()) {
        	LOG.warn("Unable to retrieve properties.", e);
            }
            addActionError("Unable to retrieve properties: " + e.toString());
        }

        if (hasErrors()) {
            return ERROR;
        } else {
            return SUCCESS;
        }
    }
