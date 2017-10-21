    private static Object buildServiceInfoVersion3(final String zone, final int port, final String name,
            final Map<String, String> properties) {
        try {
            return serviceInfoClass
                    // zone/type display name port weight priority properties
                    .getMethod("create", String.class, String.class, int.class, int.class, int.class, Map.class)
                    .invoke(null, zone, name, port, 0, 0, properties);
        } catch (final IllegalAccessException | InvocationTargetException e) {
            LOGGER.warn("Unable to invoke create method", e);
        } catch (final NoSuchMethodException e) {
            LOGGER.warn("Unable to find create method", e);
        }
        return null;
    }
