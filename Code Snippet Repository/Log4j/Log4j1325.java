    @Override
    public void unadvertise(final Object serviceInfo) {
        if (jmDNS != null) {
            try {
                final Method method = jmDNSClass.getMethod("unregisterService", serviceInfoClass);
                method.invoke(jmDNS, serviceInfo);
            } catch (final IllegalAccessException | InvocationTargetException e) {
                LOGGER.warn("Unable to invoke unregisterService method", e);
            } catch (final NoSuchMethodException e) {
                LOGGER.warn("No unregisterService method", e);
            }
        }
    }
