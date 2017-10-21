    @Override
    public Object advertise(final Map<String, String> properties) {
        // default to tcp if "protocol" was not set
        final Map<String, String> truncatedProperties = new HashMap<>();
        for (final Map.Entry<String, String> entry : properties.entrySet()) {
            if (entry.getKey().length() <= MAX_LENGTH && entry.getValue().length() <= MAX_LENGTH) {
                truncatedProperties.put(entry.getKey(), entry.getValue());
            }
        }
        final String protocol = truncatedProperties.get("protocol");
        final String zone = "._log4j._" + (protocol != null ? protocol : "tcp") + ".local.";
        // default to 4555 if "port" was not set
        final String portString = truncatedProperties.get("port");
        final int port = Integers.parseInt(portString, DEFAULT_PORT);

        final String name = truncatedProperties.get("name");

        // if version 3 is available, use it to construct a serviceInfo instance, otherwise support the version1 API
        if (jmDNS != null) {
            boolean isVersion3 = false;
            try {
                // create method is in version 3, not version 1
                jmDNSClass.getMethod("create");
                isVersion3 = true;
            } catch (final NoSuchMethodException e) {
                // no-op
            }
            Object serviceInfo;
            if (isVersion3) {
                serviceInfo = buildServiceInfoVersion3(zone, port, name, truncatedProperties);
            } else {
                serviceInfo = buildServiceInfoVersion1(zone, port, name, truncatedProperties);
            }

            try {
                final Method method = jmDNSClass.getMethod("registerService", serviceInfoClass);
                method.invoke(jmDNS, serviceInfo);
            } catch (final IllegalAccessException | InvocationTargetException e) {
                LOGGER.warn("Unable to invoke registerService method", e);
            } catch (final NoSuchMethodException e) {
                LOGGER.warn("No registerService method", e);
            }
            return serviceInfo;
        }
        LOGGER.warn("JMDNS not available - will not advertise ZeroConf support");
        return null;
    }
