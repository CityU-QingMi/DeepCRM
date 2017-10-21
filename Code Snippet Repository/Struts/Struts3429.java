    protected Map<String, String> getRunLevelDirs(String dir) {
        Map<String, String> dirs = new HashMap<String, String>();
        try {
            ResourceFinder finder = new ResourceFinder();
            URL url = finder.find("bundles");
            if (url != null) {
                if ("file".equals(url.getProtocol())) {
                    File bundlesDir = new File(url.toURI());
                    String[] runLevelDirs = bundlesDir.list(new FilenameFilter() {
                        public boolean accept(File file, String name) {
                            try {
                                return file.isDirectory() && Integer.valueOf(name) > 0;
                            } catch (NumberFormatException ex) {
                                //the name is not a number
                                return false;
                            }
                        }
                    });

                    if (runLevelDirs != null && runLevelDirs.length > 0) {
                        //add all the dirs to the list
                        for (String runLevel : runLevelDirs) {
                            dirs.put(runLevel, StringUtils.removeEnd(dir,  "/") + "/" + runLevel);
                        }
                    } else {
                        LOG.debug("No run level directories found under the [{}] directory", dir);
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
        return dirs;
    }
