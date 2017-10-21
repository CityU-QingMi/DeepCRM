    public Object nullPropertyValue(Map<String, Object> context, Object target, Object property) {
        LOG.debug("Entering nullPropertyValue [target={}, property={}]", target, property);
        boolean c = ReflectionContextState.isCreatingNullObjects(context);

        if (!c) {
            return null;
        }

        if ((target == null) || (property == null)) {
            return null;
        }

        try {
            String propName = property.toString();
            Object realTarget = reflectionProvider.getRealTarget(propName, context, target);
            Class clazz = null;

            if (realTarget != null) {
                PropertyDescriptor pd = reflectionProvider.getPropertyDescriptor(realTarget.getClass(), propName);
                if (pd == null) {
                    return null;
                }

                clazz = pd.getPropertyType();
            }

            if (clazz == null) {
                // can't do much here!
                return null;
            }

            Object param = createObject(clazz, realTarget, propName, context);

            reflectionProvider.setValue(propName, context, realTarget, param);

            return param;
        } catch (Exception e) {
            LOG.error("Could not create and/or set value back on to object", e);
        }

        return null;
    }
