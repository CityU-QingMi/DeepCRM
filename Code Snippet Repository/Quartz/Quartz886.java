    public static void closeResource(final Object resource) {

        if (resource == null)
            return;

        try {
            final Method m = resource.getClass().getMethod("close",
                    new Class[0]);

            m.invoke(resource, new Object[0]);
        } catch (final Exception e) {
        }

    }
