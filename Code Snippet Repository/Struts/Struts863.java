    private URL readJBoss5Url(Object content) {
        try {
            Method method = content.getClass().getDeclaredMethod("getHandler");
            method.setAccessible(true);
            Object handler = method.invoke(content);
            method = handler.getClass().getMethod("getRealURL");
            return (URL) method.invoke(handler);
        } catch (NoSuchMethodException e) {
            LOG.error("Provided class content [{}] is not a JBoss VirtualFile, getHandler() or getRealURL() method not found!", content.getClass().getSimpleName(), e);
        } catch (InvocationTargetException e) {
            LOG.error("Cannot invoke getHandler() or getRealURL() method!", e);
        } catch (IllegalAccessException e) {
            LOG.error("Cannot access getHandler() or getRealURL() method!", e);
        }
        return null;
    }
