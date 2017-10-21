    protected List<String> getBundlesInDir(String dir) {
        List<String> bundleJars = new ArrayList<String>();
        try {
            ResourceFinder finder = new ResourceFinder();
            URL url = finder.find(dir);
            if (url != null) {
                if ("file".equals(url.getProtocol())) {
                    File bundlesDir = new File(url.toURI());
                    File[] bundles = bundlesDir.listFiles(new FilenameFilter() {
                        public boolean accept(File file, String name) {
                            return StringUtils.endsWith(name, ".jar");
                        }
                    });

                    if (bundles != null && bundles.length > 0) {
                        //add all the bundles to the list
                        for (File bundle : bundles) {
                            String externalForm = bundle.toURI().toURL().toExternalForm();
                            LOG.debug("Adding bundle [{}]", externalForm);
                            bundleJars.add(externalForm);
                        }

                    } else {
                        LOG.debug("No bundles found under the [{}] directory", dir);
                    }
                } else {
                    LOG.warn("Unable to read [{}] directory", dir);
                }
            } else {
                LOG.warn("The [{}] directory was not found", dir);
            }
        } catch (Exception e) {
            LOG.warn("Unable load bundles from the [{}] directory", dir, e);
        }
        return bundleJars;
    }
