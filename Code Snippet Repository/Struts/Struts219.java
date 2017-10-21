    private Map<String, Object> conditionalReload(Class clazz, Map<String, Object> oldValues) throws Exception {
        Map<String, Object> mapping = oldValues;

        if (reloadingConfigs) {
            URL fileUrl = ClassLoaderUtil.getResource(buildConverterFilename(clazz), clazz);
            if (fileManager.fileNeedsReloading(fileUrl)) {
                mapping = buildConverterMapping(clazz);
            }
        }

        return mapping;
    }
