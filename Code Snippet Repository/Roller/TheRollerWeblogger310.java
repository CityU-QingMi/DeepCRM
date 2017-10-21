    public static void initializeCommonTargets() throws WebloggerException {
        String configuredVal = WebloggerConfig.getProperty(PINGS_INITIAL_COMMON_TARGETS_PROP);
        if (configuredVal == null || configuredVal.trim().length() == 0) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("No (or empty) value of " + PINGS_INITIAL_COMMON_TARGETS_PROP + " present in the configuration.  Skipping initialization of commmon targets.");
            }
            return;
        }
        PingTargetManager pingTargetMgr = WebloggerFactory.getWeblogger().getPingTargetManager();
        if (!pingTargetMgr.getCommonPingTargets().isEmpty()) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Some common ping targets are present in the database already.  Skipping initialization.");
            }
            return;
        }

        String[] configuredTargets = configuredVal.trim().split(",");
        for (int i = 0; i < configuredTargets.length; i++) {
            // Trim space around the target spec
            String thisTarget = configuredTargets[i].trim();
            // skip empty ones
            if (thisTarget.length() == 0) {
                continue;
            }
            // parse the ith target and store it
            Matcher m = NESTED_BRACE_PAIR.matcher(thisTarget);
            if (m.matches() && m.groupCount() == 2) {
                String name = m.group(1).trim();
                String url = m.group(2).trim();
                LOGGER.info("Creating common ping target '" + name + "' from configuration properties.");
                PingTarget pingTarget = new PingTarget(null, name, url, false);
                pingTargetMgr.savePingTarget(pingTarget);
            } else {
                LOGGER.error("Unable to parse configured initial ping target '" + thisTarget + "'. Skipping this target. Check your setting of the property " + PINGS_INITIAL_COMMON_TARGETS_PROP);
            }
        }
    }
