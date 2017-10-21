    @Override
    public Object getProperty(Map context, Object target, Object name) throws OgnlException {
        LOG.trace("Entering getProperty ({},{},{})", context, target, name);

        ReflectionContextState.updateCurrentPropertyPath(context, name);
        // if this is one of the regular index access
        // properties then just let the superclass deal with the
        // get.
        if (name instanceof String && contains(INDEX_ACCESS_PROPS, (String) name)) {
            return super.getProperty(context, target, name);
        }

        Object result = null;

        try{
            result = super.getProperty(context, target, name);
        } catch (ClassCastException ex) {
        }

        if (result == null) {
            //find the key class and convert the name to that class
            Class lastClass = (Class) context.get(XWorkConverter.LAST_BEAN_CLASS_ACCESSED);

            String lastProperty = (String) context.get(XWorkConverter.LAST_BEAN_PROPERTY_ACCESSED);
            if (lastClass == null || lastProperty == null) {
                return null;
            }
            Object key = getKey(context, name);
            Map map = (Map) target;
            result = map.get(key);

            if (result == null &&
                    Boolean.TRUE.equals(context.get(ReflectionContextState.CREATE_NULL_OBJECTS))
                    &&  objectTypeDeterminer.shouldCreateIfNew(lastClass,lastProperty,target,null,false)) {
                Class valueClass = objectTypeDeterminer.getElementClass(lastClass, lastProperty, key);

                try {
                    result = objectFactory.buildBean(valueClass, context);
                    map.put(key, result);
                } catch (Exception exc) {
                }
            }
        }
        return result;
    }
