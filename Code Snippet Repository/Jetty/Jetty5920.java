    public boolean isDistributable ()
    {
        boolean distributable = (
                (_webDefaultsRoot != null && _webDefaultsRoot.isDistributable())
                || (_webXmlRoot != null && _webXmlRoot.isDistributable()));

        for (WebDescriptor d : _webOverrideRoots)
            distributable&=d.isDistributable();

        if (getOrdering() != null)
        {
            List<Resource> orderedResources = getOrderedWebInfJars();
            for (Resource r: orderedResources)
            {
                FragmentDescriptor d = _webFragmentResourceMap.get(r);
                if (d!=null)
                    distributable = distributable && d.isDistributable();
            }
        }
        return distributable;
    }
