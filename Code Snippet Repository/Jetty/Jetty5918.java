    public void addFragment (Resource jarResource, Resource xmlResource)
    throws Exception
    {
        if (_metaDataComplete)
            return; //do not process anything else if web.xml/web-override.xml set metadata-complete

        //Metadata-complete is not set, or there is no web.xml
        FragmentDescriptor descriptor = new FragmentDescriptor(xmlResource);
        _webFragmentResourceMap.put(jarResource, descriptor);
        _webFragmentRoots.add(descriptor);

        descriptor.setValidating(isValidateXml());
        descriptor.parse();

        if (descriptor.getName() != null)
        {
            Descriptor existing = _webFragmentNameMap.get(descriptor.getName());
            if (existing != null && !isAllowDuplicateFragmentNames())
            {
                throw new IllegalStateException("Duplicate fragment name: "+descriptor.getName()+" for "+existing.getResource()+" and "+descriptor.getResource());
            }
            else
                _webFragmentNameMap.put(descriptor.getName(), descriptor);
        }


        //only accept an ordering from the fragment if there is no ordering already established
        if (_ordering == null && descriptor.isOrdered())
        {
            setOrdering(new RelativeOrdering(this));
            return;
        }
        
        //recompute the ordering with the new fragment name
        orderFragments();
    }
