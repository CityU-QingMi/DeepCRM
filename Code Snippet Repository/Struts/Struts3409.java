    public List<PackageConfig> loadPackages(Bundle bundle, BundleContext bundleContext, ObjectFactory objectFactory,
                                            FileManagerFactory fileManagerFactory, Map<String, PackageConfig> pkgConfigs) throws ConfigurationException {
        Configuration config = new DefaultConfiguration("struts.xml");
        BundleConfigurationProvider prov = new BundleConfigurationProvider("struts.xml", bundle, bundleContext);
        for (PackageConfig pkg : pkgConfigs.values()) {
            config.addPackageConfig(pkg.getName(), pkg);
        }
        prov.setObjectFactory(objectFactory);
        prov.setFileManagerFactory(fileManagerFactory);
        prov.init(config);
        prov.loadPackages();

        List<PackageConfig> list = new ArrayList<PackageConfig>(config.getPackageConfigs().values());
        list.removeAll(pkgConfigs.values());

        return list;
    }
