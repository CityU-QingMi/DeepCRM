    private static void addURLs(String dirPath, List urlList) throws Exception {
        File libDir = new File(dirPath);
        String[] libJarNames = libDir.list(new FilenameFilter() {
            public boolean accept(File dir, String pathname) {
                if (pathname.endsWith(".jar")) {
                    return true;
                }
                return false;
            }
        });       
        for (int i=0; i<libJarNames.length; i++) {
            String url = "file://" + dirPath + FS + libJarNames[i];
            urlList.add(new URL(url));
        }
    }
