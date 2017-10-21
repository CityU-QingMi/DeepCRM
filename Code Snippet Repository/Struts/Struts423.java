    public ResourceFinder(String path, ClassLoaderInterface classLoaderInterface, URL... urls) {
        path = StringUtils.trimToEmpty(path);
        if (!StringUtils.endsWith(path, "/")) {
            path += "/";
        }
        this.path = path;

        this.classLoaderInterface = classLoaderInterface == null ? new ClassLoaderInterfaceDelegate(Thread.currentThread().getContextClassLoader()) : classLoaderInterface ;

        for (int i = 0; urls != null && i < urls.length; i++) {
            URL url = urls[i];
            if (url == null || isDirectory(url) || "jar".equals(url.getProtocol())) {
                continue;
            }
            try {
                urls[i] = new URL("jar", "", -1, url.toString() + "!/");
            } catch (MalformedURLException e) {
            }
        }
        this.urls = (urls == null || urls.length == 0)? null : urls;
    }
