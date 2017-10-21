    private void readFile(List<URL> urls, File physicalFile) throws MalformedURLException {
        File[] files = physicalFile.listFiles();
        if (physicalFile.isDirectory() && files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    addIfAbsent(urls, file.toURI().toURL());
                } else if (file.isDirectory()) {
                    readFile(urls, file);
                }
            }
        }
    }
