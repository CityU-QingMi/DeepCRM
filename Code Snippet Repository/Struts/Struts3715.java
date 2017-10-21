    private void reload(File file) {
        if (classLoader != null) {
            LOG.debug("Change detected in file [{}], reloading class loader", file.getAbsolutePath());
            classLoader.reload();
            if (reloadConfig && Dispatcher.getInstance() != null) {
                LOG.debug("Change detected in file [{}], reloading configuration", file.getAbsolutePath());
                Dispatcher.getInstance().getConfigurationManager().reload();
            }
        }
    }
