    private static Object createJmDnsVersion3() {
        try {
            final Method jmDNSCreateMethod = jmDNSClass.getMethod("create");
            return jmDNSCreateMethod.invoke(null, (Object[]) null);
        } catch (final IllegalAccessException | InvocationTargetException e) {
            LOGGER.warn("Unable to invoke create method", e);
        } catch (final NoSuchMethodException e) {
            LOGGER.warn("Unable to get create method", e);
        }
        return null;
    }
