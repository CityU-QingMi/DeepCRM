    private List<URL> getAllJBossPhysicalUrls(URL url) throws IOException {
        List<URL> urls = new ArrayList<URL>();
        Object content = url.openConnection().getContent();
        String classContent = content.getClass().toString();
        if (classContent.startsWith("class org.jboss.vfs.VirtualFile")) {
            File physicalFile = readJBossPhysicalFile(content);
            if (physicalFile != null) {
                readFile(urls, physicalFile);
                readFile(urls, physicalFile.getParentFile());
            }
        } else if (classContent.startsWith("class org.jboss.virtual.VirtualFile")) {
            URL physicalUrl = readJBoss5Url(content);
            if (physicalUrl != null) {
                urls.add(physicalUrl);
            }
        } else {
            urls.add(url);
        }
        return urls;
    }
