    public static void stop() {
        if (profiler != null) {
            try {
                controllerClazz
                        .getMethod("captureSnapshot", long.class)
                        .invoke(profiler, snapshotWithoutHeap());
                controllerClazz
                        .getMethod("stopCPUProfiling")
                        .invoke(profiler);
            }
            catch (final Exception e) {
                LOGGER.error("Profiler was active, but failed.", e);
            }
        }
    }
