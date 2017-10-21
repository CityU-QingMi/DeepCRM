    public static void start() {

        if (profiler != null) {
            try {
                controllerClazz
                        .getMethod("startCPUProfiling", long.class, String.class)
                        .invoke(profiler, cpuSampling(), Strings.EMPTY);
            }
            catch (final Exception e) {
                LOGGER.error("Profiler was active, but failed.", e);
            }
        }
    }
