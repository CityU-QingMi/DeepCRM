    private Object createObject(Class clazz, Object target, String property, Map<String, Object> context) throws Exception {
        if (Set.class.isAssignableFrom(clazz)) {
            return new HashSet();
        } else if (Collection.class.isAssignableFrom(clazz)) {
            return new ArrayList();
        } else if (clazz == Map.class) {
            return new HashMap();
        } else if (clazz == EnumMap.class) {
            Class keyClass = objectTypeDeterminer.getKeyClass(target.getClass(), property);
            return new EnumMap(keyClass);
        }

        return objectFactory.buildBean(clazz, context);
    }
