    public List<PackageConfig> getPackages() {
        List<PackageConfig> pkgs = new ArrayList<PackageConfig>();
        Bundle bundle = getBundle();
        if (bundle.getState() == Bundle.ACTIVE) {
            for (String name : bundleAccessor.getPackagesByBundle(bundle)) {
                PackageConfig packageConfig = configuration.getPackageConfig(name);
                if (packageConfig != null)
                    pkgs.add(packageConfig);
            }
        }
        return pkgs;
    }
