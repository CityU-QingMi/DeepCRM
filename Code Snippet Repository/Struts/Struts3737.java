    public StrutsWildcardServletApplicationContext(ServletContext context) {
        super(context);

        Set<URL> urls = new HashSet<>();

        for (Object path : context.getResourcePaths("/")) {
            try {
                String realPath = context.getRealPath(String.valueOf(path));

                if (realPath != null) {
                    URL url = new File(realPath).toURI().toURL();
                    urls.add(url);
                }
            } catch (MalformedURLException e) {
                throw new ConfigurationException(e);
            }
        }

        try {
            Enumeration<URL> resources = getClass().getClassLoader().getResources("/");
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                urls.add(resource);
            }
        } catch (IOException e) {
            throw new ConfigurationException(e);
        }

        finder = new ResourceFinder(urls.toArray(new URL[urls.size()]));
    }
