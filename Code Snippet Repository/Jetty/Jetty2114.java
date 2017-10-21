    protected Resource indexBundle(Bundle bundle) throws Exception
    {
        File bundleFile = BundleFileLocatorHelperFactory.getFactory().getHelper().getBundleInstallLocation(bundle);
        Resource resource = Resource.newResource(bundleFile.toURI());
        URI uri = resource.getURI();
        _uriToBundle.putIfAbsent(uri,bundle);
        _bundleToUri.putIfAbsent(bundle,uri);
        _bundleToResource.putIfAbsent(bundle,resource);
        _resourceToBundle.putIfAbsent(resource,bundle);
        return resource;
    }
