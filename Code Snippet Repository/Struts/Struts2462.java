    public DefaultClassFinder(ClassLoaderInterface classLoaderInterface, Collection<URL> urls, boolean extractBaseInterfaces, Set<String> protocols, Test<String> classNameFilter) {
        this.classLoaderInterface = classLoaderInterface;
        this.extractBaseInterfaces = extractBaseInterfaces;
        this.fileManager = ActionContext.getContext().getInstance(FileManagerFactory.class).getFileManager();

        List<String> classNames = new ArrayList<>();
        for (URL location : urls) {
            try {
                if (protocols.contains(location.getProtocol())) {
                    classNames.addAll(jar(location));
                } else if ("file".equals(location.getProtocol())) {
                    try {
                        // See if it's actually a jar
                        URL jarUrl = new URL("jar", "", location.toExternalForm() + "!/");
                        JarURLConnection juc = (JarURLConnection) jarUrl.openConnection();
                        juc.getJarFile();
                        classNames.addAll(jar(jarUrl));
                    } catch (IOException e) {
                        classNames.addAll(file(location));
                    }
                }
            } catch (Exception e) {
                LOG.error("Unable to read URL [{}]", location.toExternalForm(), e);
            }
        }

        for (String className : classNames) {
            try {
                if (classNameFilter.test(className))
                    readClassDef(className);
            } catch (Throwable e) {
                LOG.error("Unable to read class [{}]", className, e);
            }
        }
    }
