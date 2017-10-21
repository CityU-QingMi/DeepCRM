    public void addWebFragments (final WebAppContext context, final MetaData metaData) throws Exception
    {
        @SuppressWarnings("unchecked")
        Map<Resource, Resource> frags = (Map<Resource,Resource>)context.getAttribute(FRAGMENT_RESOURCES);
        if (frags!=null)
        {
            for (Resource key : frags.keySet())
            {
                if (key.isDirectory()) //tolerate the case where the library is a directory, not a jar. useful for OSGi for example
                {
                    metaData.addFragment(key, frags.get(key));                          
                }
                else //the standard case: a jar most likely inside WEB-INF/lib
                {
                    metaData.addFragment(key, frags.get(key));
                }
            }
        }
    }
