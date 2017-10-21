    private void reloadRequiredPackages(List<Element> reloads) {
        if (reloads.size() > 0) {
            List<Element> result = new ArrayList<>();
            for (Element pkg : reloads) {
                PackageConfig cfg = addPackage(pkg);
                if (cfg.isNeedsRefresh()) {
                    result.add(pkg);
                }
            }
            if ((result.size() > 0) && (result.size() != reloads.size())) {
                reloadRequiredPackages(result);
                return;
            }

            // Print out error messages for all misconfigured inheritance packages
            if (result.size() > 0) {
                for (Element rp : result) {
                    String parent = rp.getAttribute("extends");
                    if (parent != null) {
                        List<PackageConfig> parents = ConfigurationUtil.buildParentsFromString(configuration, parent);
                        if (parents != null && parents.size() <= 0) {
                            LOG.error("Unable to find parent packages {}", parent);
                        }
                    }
                }
            }
        }
    }
