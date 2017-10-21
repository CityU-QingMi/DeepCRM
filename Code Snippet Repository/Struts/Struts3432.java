    protected void addExportedPackages(Properties strutsConfigProps, Properties configProps) {
        String[] rootPackages = StringUtils.split((String) strutsConfigProps.get("scanning.package.includes"), ",");
        ResourceFinder finder = new ResourceFinder(StringUtils.EMPTY);
        List<String> exportedPackages = new ArrayList<String>();
        //build a list of subpackages
        for (String rootPackage : rootPackages) {
            try {
                String version = null;
                if (rootPackage.indexOf(";") > 0) {
                    String[] splitted = rootPackage.split(";");
                    rootPackage = splitted[0];
                    version = splitted[1];
                }
                Map<URL, Set<String>> subpackagesMap = finder.findPackagesMap(StringUtils.replace(rootPackage.trim(), ".", "/"));
                for (Map.Entry<URL, Set<String>> entry : subpackagesMap.entrySet()) {
                    URL url = entry.getKey();
                    Set<String> packages = entry.getValue();

                    //get version if not set
                    if (StringUtils.isBlank(version)) {
                        version = getVersion(url);
                    }

                    if (packages != null) {
                        for (String subpackage : packages) {
                            exportedPackages.add(subpackage + "; version=" + version);
                        }
                    }
                }
            } catch (IOException e) {
                LOG.error("Unable to find subpackages of [{}]", rootPackage, e);
            }
        }

        //make a string with the exported packages and add it to the system properties
        if (!exportedPackages.isEmpty()) {
            String systemPackages = (String) configProps.get(Constants.FRAMEWORK_SYSTEMPACKAGES);
            systemPackages = StringUtils.removeEnd(systemPackages, ",") + "," + StringUtils.join(exportedPackages, ",");
            configProps.put(Constants.FRAMEWORK_SYSTEMPACKAGES, systemPackages);
        }
    }
