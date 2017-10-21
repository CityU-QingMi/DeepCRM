    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder("Provider[");
        if (!DEFAULT_PRIORITY.equals(priority)) {
            result.append("priority=").append(priority).append(", ");
        }
        if (threadContextMap != null) {
            result.append("threadContextMap=").append(threadContextMap).append(", ");
        } else if (threadContextMapClass != null) {
            result.append("threadContextMapClass=").append(threadContextMapClass.getName());
        }
        if (className != null) {
            result.append("className=").append(className).append(", ");
        } else if (loggerContextFactoryClass != null) {
            result.append("class=").append(loggerContextFactoryClass.getName());
        }
        if (url != null) {
            result.append("url=").append(url);
        }
        final ClassLoader loader;
        if (classLoader == null || (loader = classLoader.get()) == null) {
            result.append(", classLoader=null(not reachable)");
        } else {
            result.append(", classLoader=").append(loader);
        }
        result.append("]");
        return result.toString();
    }
