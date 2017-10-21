    private Object convert(Object element) {
        if ((element != null) && !clazz.isAssignableFrom(element.getClass())) {
            // convert to correct type
            LOG.debug("Converting from {} to {}", element.getClass().getName(), clazz.getName());
            TypeConverter converter = getTypeConverter();
            Map<String, Object> context = ActionContext.getContext().getContextMap();
            element = converter.convertValue(context, null, null, null, element, clazz);
        }

        return element;
    }
