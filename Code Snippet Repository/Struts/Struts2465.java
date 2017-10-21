    private List<String> file(URL location) {
        List<String> classNames = new ArrayList<>();
        File dir = new File(URLDecoder.decode(location.getPath()));
        if ("META-INF".equals(dir.getName())) {
            dir = dir.getParentFile(); // Scrape "META-INF" off
        }
        if (dir.isDirectory()) {
            scanDir(dir, classNames, "");
        }
        return classNames;
    }
