    private Throwable getThrowable(final Class<Throwable> throwableClass, final String message, final Throwable cause) {
        try {
            @SuppressWarnings("unchecked")
            final
            Constructor<Throwable>[] constructors = (Constructor<Throwable>[]) throwableClass.getConstructors();
            for (final Constructor<Throwable> constructor : constructors) {
                final Class<?>[] parameterTypes = constructor.getParameterTypes();
                if (parameterTypes.length == 2) {
                    if (String.class == parameterTypes[0] && Throwable.class.isAssignableFrom(parameterTypes[1])) {
                        return constructor.newInstance(message, cause);
                    } else if (String.class == parameterTypes[1] &&
                            Throwable.class.isAssignableFrom(parameterTypes[0])) {
                        return constructor.newInstance(cause, message);
                    }
                }
            }
            return null;
        } catch (final Exception e) {
            return null;
        }
    }
