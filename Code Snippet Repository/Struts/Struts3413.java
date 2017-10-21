    public List<URL> loadResources(String name, boolean translate) throws IOException {
        Bundle bundle = getCurrentBundle();
        if (bundle != null) {
            List<URL> resources = new ArrayList<>();
            Enumeration e = bundle.getResources(name);
            if (e != null) {
                while (e.hasMoreElements()) {
                    resources.add(translate ? OsgiUtil.translateBundleURLToJarURL((URL) e.nextElement(), getCurrentBundle()) : (URL) e.nextElement());
                }
            }
            return resources;
        }

        return null;
    }
