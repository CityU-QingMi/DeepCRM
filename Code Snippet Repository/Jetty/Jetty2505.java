    public AttributeNormalizer(Resource baseResource)
    {
        if (baseResource==null)
            throw new IllegalArgumentException("No base resource!");
            
        warURI = toCanonicalURI(baseResource.getURI());
        if (!warURI.isAbsolute())
            throw new IllegalArgumentException("WAR URI is not absolute: " + warURI);
        
        add(paths,uris,"jetty.base",9);
        add(paths,uris,"jetty.home",8);
        add(paths,uris,"user.home",7);
        add(paths,uris,"user.dir",6);

        if (warURI.getScheme().equalsIgnoreCase("file"))
            paths.add(new PathAttribute("WAR.path",toCanonicalPath(new File(warURI).toString()),10));
        uris.add(new URIAttribute("WAR.uri", warURI,9)); // preferred encoding
        uris.add(new URIAttribute("WAR", warURI,8)); // legacy encoding
        
        Collections.sort(paths,attrComparator);
        Collections.sort(uris,attrComparator);
        
        Stream.concat(paths.stream(),uris.stream()).forEach(a->attributes.put(a.key,a));        
        
        if (LOG.isDebugEnabled())
        {
            for (Attribute attr : attributes.values())
            {
                LOG.debug(attr.toString());
            }
        }
    }
