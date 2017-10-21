    public static List<PackageConfig> buildParentsFromString(Configuration configuration, String parent) {
        List<String> parentPackageNames = buildParentListFromString(parent);
        List<PackageConfig> parentPackageConfigs = new ArrayList<>();
        for (String parentPackageName : parentPackageNames) {
            PackageConfig parentPackageContext = configuration.getPackageConfig(parentPackageName);

            if (parentPackageContext != null) {
                parentPackageConfigs.add(parentPackageContext);
            }
        }

        return parentPackageConfigs;
    }
