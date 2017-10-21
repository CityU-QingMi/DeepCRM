    private List<URL> findBundles() throws Exception {
        ArrayList<URL> list = new ArrayList<URL>();
        for (Object o : this.servletContext.getResourcePaths(BUNDLES_DIR)) {
            String name = (String) o;
            if (name.endsWith(".jar")) {
                URL url = this.servletContext.getResource(name);
                if (url != null) {
                    list.add(url);
                }
            }
        }

        ProtectionDomain protectionDomain = ShellService.class.getProtectionDomain();
        CodeSource codeSource = protectionDomain.getCodeSource();
        URL loc = codeSource.getLocation();
        list.add(loc);

        return list;
    }
