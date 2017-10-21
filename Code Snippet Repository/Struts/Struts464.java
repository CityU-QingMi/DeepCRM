    public static Revision build(URL fileUrl, FileManager fileManager) {
        // File within a Jar
        // Find separator index of jar filename and filename within jar
        String jarFileName = "";
        try {
            String fileName = fileUrl.toString();
            int separatorIndex = fileName.indexOf(JAR_FILE_NAME_SEPARATOR);
            if (separatorIndex == -1) {
                separatorIndex = fileName.lastIndexOf(JAR_FILE_EXTENSION_END);
            }
            if (separatorIndex == -1) {
                LOG.warn("Could not find end of jar file!");
                return null;
            }

            // Split file name
            jarFileName = fileName.substring(0, separatorIndex);
            int index = separatorIndex + JAR_FILE_NAME_SEPARATOR.length();
            String fileNameInJar = fileName.substring(index).replaceAll("%20", " ");

            URL url = fileManager.normalizeToFileProtocol(fileUrl);
            if (url != null) {
                JarFile jarFile = new JarFile(FileUtils.toFile(url));
                ZipEntry entry = jarFile.getEntry(fileNameInJar);
                return new JarEntryRevision(jarFileName, fileNameInJar, entry.getTime());
            } else {
                return null;
            }
        } catch (Throwable e) {
            LOG.warn("Could not create JarEntryRevision for [{}]!", jarFileName, e);
            return null;
        }
    }
