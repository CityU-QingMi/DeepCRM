    protected XMLConfigurer loadFile(String fileName, Class clazz, boolean checkFile) {
        URL fileUrl = ClassLoaderUtil.getResource(fileName, clazz);
        if ((checkFile && fileManager.fileNeedsReloading(fileUrl)) || !validatorFileCache.containsKey(fileName)) {

            try (InputStream is = fileManager.loadFile(fileUrl)) {
                if (is != null) {
                    LOG.debug("Loading validation xml file [{}]", fileName);
                    XMLConfigurer configurer = new XMLConfigurer();
                    configurer.fromXML(is);
                    validatorFileCache.put(fileName, configurer);
                    return configurer;
                }
            } catch (IOException e) {
                LOG.error("Unable to close input stream for [{}] ", fileName, e);
            }
        } else {
            return (XMLConfigurer) validatorFileCache.get(fileName);
        }

        return null;
    }
