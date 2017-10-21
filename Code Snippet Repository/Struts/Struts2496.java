    protected void buildIndexActions(Map<String, PackageConfig.Builder> packageConfigs) {
        Map<String, PackageConfig.Builder> byNamespace = new HashMap<>();
        Collection<PackageConfig.Builder> values = packageConfigs.values();
        for (PackageConfig.Builder packageConfig : values) {
            byNamespace.put(packageConfig.getNamespace(), packageConfig);
        }

        // Step #1
        Set<String> namespaces = byNamespace.keySet();
        for (String namespace : namespaces) {
            // First see if the namespace has an index action
            PackageConfig.Builder pkgConfig = byNamespace.get(namespace);
            ActionConfig indexActionConfig = pkgConfig.build().getAllActionConfigs().get("index");
            if (indexActionConfig == null) {
                continue;
            }

            // Step #2
            if (!redirectToSlash) {
                int lastSlash = namespace.lastIndexOf('/');
                if (lastSlash >= 0) {
                    String parentAction = namespace.substring(lastSlash + 1);
                    String parentNamespace = namespace.substring(0, lastSlash);
                    PackageConfig.Builder parent = byNamespace.get(parentNamespace);
                    if (parent == null || parent.build().getAllActionConfigs().get(parentAction) == null) {
                        if (parent == null) {
                            parent = new PackageConfig.Builder(parentNamespace).namespace(parentNamespace).
                                    addParents(pkgConfig.build().getParents());
                            packageConfigs.put(parentNamespace, parent);
                        }

                        if (parent.build().getAllActionConfigs().get(parentAction) == null) {
                            parent.addActionConfig(parentAction, indexActionConfig);
                        }
                    } else {
                        LOG.trace("The parent namespace [{}] already contains an action [{}]", parentNamespace, parentAction);
                    }
                }
            }

            // Step #3
            if (pkgConfig.build().getAllActionConfigs().get("") == null) {
                LOG.trace("Creating index ActionConfig with an action name of [] for the action class [{}]", indexActionConfig.getClassName());
                pkgConfig.addActionConfig("", indexActionConfig);
            }
        }
    }
