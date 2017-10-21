    public static List<Field> getAllDeclaredFields(Class<?> cls) {
        final List<Field> fields = new ArrayList<>();
        while (cls != null) {
            for (final Field field : cls.getDeclaredFields()) {
                fields.add(field);
            }
            cls = cls.getSuperclass();
        }
        return fields;
    }
