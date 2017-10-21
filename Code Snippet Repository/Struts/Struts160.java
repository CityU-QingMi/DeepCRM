    protected PackageConfig.Builder buildPackageContext(Element packageElement) {
        String parent = packageElement.getAttribute("extends");
        String abstractVal = packageElement.getAttribute("abstract");
        boolean isAbstract = Boolean.parseBoolean(abstractVal);
        String name = StringUtils.defaultString(packageElement.getAttribute("name"));
        String namespace = StringUtils.defaultString(packageElement.getAttribute("namespace"));

        // Strict DMI is enabled by default, it can disabled by user
        boolean strictDMI = true;
        if (packageElement.hasAttribute("strict-method-invocation")) {
            strictDMI = Boolean.parseBoolean(packageElement.getAttribute("strict-method-invocation"));
        }

        PackageConfig.Builder cfg = new PackageConfig.Builder(name)
                .namespace(namespace)
                .isAbstract(isAbstract)
                .strictMethodInvocation(strictDMI)
                .location(DomHelper.getLocationObject(packageElement));

        if (StringUtils.isNotEmpty(StringUtils.defaultString(parent))) { // has parents, let's look it up
            List<PackageConfig> parents = new ArrayList<>();
            for (String parentPackageName : ConfigurationUtil.buildParentListFromString(parent)) {
                if (configuration.getPackageConfigNames().contains(parentPackageName)) {
                    parents.add(configuration.getPackageConfig(parentPackageName));
                } else if (declaredPackages.containsKey(parentPackageName)) {
                    if (configuration.getPackageConfig(parentPackageName) == null) {
                        addPackage(declaredPackages.get(parentPackageName));
                    }
                    parents.add(configuration.getPackageConfig(parentPackageName));
                } else {
                    throw new ConfigurationException("Parent package is not defined: " + parentPackageName);
                }

            }

            if (parents.size() <= 0) {
                cfg.needsRefresh(true);
            } else {
                cfg.addParents(parents);
            }
        }

        return cfg;
    }
