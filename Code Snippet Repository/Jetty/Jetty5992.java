    @Override
    public void preConfigure (WebAppContext context) throws Exception
    {
        //parse webdefault.xml
        String defaultsDescriptor = context.getDefaultsDescriptor();
        if (defaultsDescriptor != null && defaultsDescriptor.length() > 0)
        {
            Resource dftResource = Resource.newSystemResource(defaultsDescriptor);
            if (dftResource == null) 
                dftResource = context.newResource(defaultsDescriptor);
            context.getMetaData().setDefaults (dftResource);
        }
        
        //parse, but don't process web.xml
        Resource webxml = findWebXml(context);
        if (webxml != null) 
        {      
            context.getMetaData().setWebXml(webxml);
            context.getServletContext().setEffectiveMajorVersion(context.getMetaData().getWebXml().getMajorVersion());
            context.getServletContext().setEffectiveMinorVersion(context.getMetaData().getWebXml().getMinorVersion());
        }
        
        //parse but don't process override-web.xml
        for (String overrideDescriptor : context.getOverrideDescriptors())
        {
            if (overrideDescriptor != null && overrideDescriptor.length() > 0)
            {
                Resource orideResource = Resource.newSystemResource(overrideDescriptor);
                if (orideResource == null) 
                    orideResource = context.newResource(overrideDescriptor);
                context.getMetaData().addOverride(orideResource);
            }
        }
    }
