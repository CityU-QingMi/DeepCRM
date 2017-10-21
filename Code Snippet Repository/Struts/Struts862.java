    private File readJBossPhysicalFile(Object content) {
        try {
            Method method = content.getClass().getDeclaredMethod("getPhysicalFile");
            return (File) method.invoke(content);
        } catch (NoSuchMethodException e) {
            LOG.error("Provided class content [{}] is not a JBoss VirtualFile, getPhysicalFile() method not found!", content.getClass().getSimpleName(), e);
        } catch (InvocationTargetException e) {
            LOG.error("Cannot invoke getPhysicalFile() method!", e);
        } catch (IllegalAccessException e) {
            LOG.error("Cannot access getPhysicalFile() method!", e);
        }
        return null;
    }
