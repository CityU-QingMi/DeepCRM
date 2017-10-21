    private static Object buildServiceInfoVersion1(final String zone, final int port, final String name,
            final Map<String, String> properties) {
        // version 1 uses a hashtable
        @SuppressWarnings("UseOfObsoleteCollectionType")
        final Hashtable<String, String> hashtableProperties = new Hashtable<>(properties);
        try {
            return serviceInfoClass.getConstructor(String.class, String.class, int.class, int.class, int.class,
                    Hashtable.class).newInstance(zone, name, port, 0, 0, hashtableProperties);
        } catch (final IllegalAccessException | InstantiationException | InvocationTargetException e) {
            LOGGER.warn("Unable to construct ServiceInfo instance", e);
        } catch (final NoSuchMethodException e) {
            LOGGER.warn("Unable to get ServiceInfo constructor", e);
        }
        return null;
    }
