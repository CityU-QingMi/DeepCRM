    public static PluginRegistry getInstance() {
        PluginRegistry result = INSTANCE;
        if (result == null) {
            synchronized (INSTANCE_LOCK) {
                result = INSTANCE;
                if (result == null) {
                    INSTANCE = result = new PluginRegistry();
                }
            }
        }
        return result;
    }
