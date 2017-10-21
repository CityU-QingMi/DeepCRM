    private static boolean isPagePlugin(Class pluginClass) {
        Class[] interfaces = pluginClass.getInterfaces();
        if (interfaces != null) {
            for (Class clazz : interfaces) {
                if (clazz.equals(WeblogEntryPlugin.class)) {
                    return true;
                }
            }
        }
        return false;
    }
