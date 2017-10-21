    protected String getVersion(URL url) {
        if ("jar".equals(url.getProtocol())) {
            try {
                FileManager fileManager = ServletActionContext.getContext().getInstance(FileManagerFactory.class).getFileManager();
                JarFile jarFile = new JarFile(new File(fileManager.normalizeToFileProtocol(url).toURI()));
                Manifest manifest = jarFile.getManifest();
                if (manifest != null) {
                    String version = manifest.getMainAttributes().getValue("Bundle-Version");
                    if (StringUtils.isNotBlank(version)) {
                        return getVersionFromString(version);
                    }
                } else {
                    //try to get the version from the file name
                    return getVersionFromString(jarFile.getName());
                }
            } catch (Exception e) {
                LOG.error("Unable to extract version from [{}], defaulting to '1.0.0'", url.toExternalForm());
            }
        }
        return "1.0.0";
    }
