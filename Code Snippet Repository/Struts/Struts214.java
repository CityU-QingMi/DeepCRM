    public TypeConverter lookup(Class clazz) {
        TypeConverter result = lookup(clazz.getName(), clazz.isPrimitive());

        if (result == null && clazz.isPrimitive()) {
/**/
/**/
/**/
/**/
            return defaultTypeConverter;
        }

        return result;
    }
