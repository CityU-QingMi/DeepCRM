    private Class doConvertToClass(Object value) {
        Class clazz = null;
        if (value != null && value instanceof String && ((String) value).length() > 0) {
            try {
                clazz = Class.forName((String) value);
            } catch (ClassNotFoundException e) {
                throw new XWorkException(e.getLocalizedMessage(), e);
            }
        }
        return clazz;
    }
