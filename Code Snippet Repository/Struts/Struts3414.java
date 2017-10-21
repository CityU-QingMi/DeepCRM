    public URL loadResourceFromAllBundles(String name) throws IOException {
        for (Map.Entry<String, Bundle> entry : osgiHost.getActiveBundles().entrySet()) {
            Enumeration e = entry.getValue().getResources(name);
            if (e != null && e.hasMoreElements()) {
                return (URL) e.nextElement();
            }
        }

        return null;
    }
