    private Throwable getThrowable(final Class<Throwable> throwableClass, final Throwable cause) {
        try {
            @SuppressWarnings("unchecked")
            final
            Constructor<Throwable>[] constructors = (Constructor<Throwable>[]) throwableClass.getConstructors();
            for (final Constructor<Throwable> constructor : constructors) {
                final Class<?>[] parameterTypes = constructor.getParameterTypes();
                if (parameterTypes.length == 1 && Throwable.class.isAssignableFrom(parameterTypes[0])) {
                    return constructor.newInstance(cause);
                }
            }
            return null;
        } catch (final Exception e) {
            return null;
        }
    }
