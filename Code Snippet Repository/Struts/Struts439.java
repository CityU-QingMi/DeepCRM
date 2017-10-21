     private static void readSubDirectories(File dir, String basePath, Set<String> resources) throws MalformedURLException {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    String name = file.getName();
                    String subName = StringUtils.removeEnd(basePath, "/") + "/" + name;
                    resources.add(subName);
                    readSubDirectories(file, subName, resources);
                }
            }
        }
    }
