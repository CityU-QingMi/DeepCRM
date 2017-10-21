    public boolean needsReloading() {
        ZipEntry entry;
        try {
            JarFile jarFile = new JarFile(this.jarFileName);
            entry = jarFile.getEntry(this.fileNameInJar);
        } catch (IOException e) {
            entry = null;
        }

        return entry != null && (lastModified < entry.getTime());
    }
