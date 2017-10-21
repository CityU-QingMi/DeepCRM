    public List<FragmentDescriptor> getOrderedFragments ()
    {
        List<FragmentDescriptor> list = new ArrayList<FragmentDescriptor>();
        if (getOrdering() == null)
            return list;

        for (Resource r:getOrderedWebInfJars())
        {
            FragmentDescriptor fd = _webFragmentResourceMap.get(r);
            if (fd != null)
                list.add(fd);
        }
        return list;
    }
