    private static void set(Object target, String method, String value)
            throws SchedulerException {
        final Method setter;

        try {
            setter = target.getClass().getMethod(method, String.class);
        } catch (SecurityException e) {
            LOGGER.error("A SecurityException occured: " + e.getMessage(), e);
            return;
        } catch (NoSuchMethodException e) {
            // This probably won't happen since the interface has the method
            LOGGER.warn(target.getClass().getName()
                    + " does not contain public method " + method + "(String)");
            return;
        }

        if (Modifier.isAbstract(setter.getModifiers())) {
            // expected if method not implemented (but is present on
            // interface)
            LOGGER.warn(target.getClass().getName()
                    + " does not implement " + method
                    + "(String)");
            return;
        }

        try {
            setter.invoke(target, value);
        } catch (InvocationTargetException ite) {
            throw new SchedulerException(ite.getTargetException());
        } catch (Exception e) {
            throw new SchedulerException(e);
        }
    }
