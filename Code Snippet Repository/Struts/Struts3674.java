    private static void findJars(File file, ArrayList<URL> urls) throws MalformedURLException {
        File[] files = file.listFiles();
        if (files == null) {
            return;
        }

        for (File f : files) {
            if (f.isDirectory()) {
                findJars(f, urls);
            } else if (f.getName().endsWith(".jar")) {
                if (isValid(f.getName())) {
                    urls.add(f.toURL());
                }
            }
        }
    }
