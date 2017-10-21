    private static void unregisterAllMatching(final String search, final MBeanServer mbs) {
        try {
            final ObjectName pattern = new ObjectName(search);
            final Set<ObjectName> found = mbs.queryNames(pattern, null);
            if (found.isEmpty()) {
            	LOGGER.trace("Unregistering but no MBeans found matching '{}'", search);
            } else {
            	LOGGER.trace("Unregistering {} MBeans: {}", found.size(), found);
            }
            for (final ObjectName objectName : found) {
                mbs.unregisterMBean(objectName);
            }
        } catch (final InstanceNotFoundException ex) {
            LOGGER.debug("Could not unregister MBeans for " + search + ". Ignoring " + ex);
        } catch (final Exception ex) {
            LOGGER.error("Could not unregister MBeans for " + search, ex);
        }
    }
