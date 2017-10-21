    public void addFilterMapping (String filterName, XmlParser.Node node, WebAppContext context, Descriptor descriptor)
    {
        FilterMapping mapping = new FilterMapping();
        mapping.setFilterName(filterName);

        List<String> paths = new ArrayList<String>();
        Iterator<XmlParser.Node>  iter = node.iterator("url-pattern");
        while (iter.hasNext())
        {
            String p = iter.next().toString(false, true);
            p = ServletPathSpec.normalize(p);
            paths.add(p);
            context.getMetaData().setOrigin(filterName+".filter.mapping."+p, descriptor);
        }
        mapping.setPathSpecs((String[]) paths.toArray(new String[paths.size()]));

        List<String> names = new ArrayList<String>();
        iter = node.iterator("servlet-name");
        while (iter.hasNext())
        {
            String n = ((XmlParser.Node) iter.next()).toString(false, true);
            names.add(n);
        }
        mapping.setServletNames((String[]) names.toArray(new String[names.size()]));


        List<DispatcherType> dispatches = new ArrayList<DispatcherType>();
        iter=node.iterator("dispatcher");
        while(iter.hasNext())
        {
            String d=((XmlParser.Node)iter.next()).toString(false,true);
            dispatches.add(FilterMapping.dispatch(d));
        }

        if (dispatches.size()>0)
            mapping.setDispatcherTypes(EnumSet.copyOf(dispatches));

        _filterMappings.add(mapping);
    }
