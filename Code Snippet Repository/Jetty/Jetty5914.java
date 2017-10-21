    public void clear ()
    {
        _webDefaultsRoot = null;
        _origins.clear();
        _webXmlRoot = null;
        _webOverrideRoots.clear();
        _metaDataComplete = false;
        _annotations.clear();
        _descriptorProcessors.clear();
        _webFragmentRoots.clear();
        _webFragmentNameMap.clear();
        _webFragmentResourceMap.clear();
        _annotations.clear();
        _webInfJars.clear();
        _orderedWebInfResources.clear();
        _orderedContainerResources.clear();
        _ordering = null;
        _allowDuplicateFragmentNames = false;
    }
