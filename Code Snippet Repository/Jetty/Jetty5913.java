    public Resource getJarForFragment (String name)
    {
        FragmentDescriptor f = getFragment(name);
        if (f == null)
            return null;

        Resource jar = null;
        for (Resource r: _webFragmentResourceMap.keySet())
        {
            if (_webFragmentResourceMap.get(r).equals(f))
                jar = r;
        }
        return jar;
    }
