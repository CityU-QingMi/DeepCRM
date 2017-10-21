    public Object convertValue(Map context, Object o, Class toClass) {
        if (toClass.equals(String.class)) {
            return convertToString(context, o);
        } else if (o instanceof String[]) {
            return convertFromString(context, (String[]) o, toClass);
        } else if (o instanceof String) {
            return convertFromString(context, new String[]{(String) o}, toClass);
        } else {
            return performFallbackConversion(context, o, toClass);
        }
    }
