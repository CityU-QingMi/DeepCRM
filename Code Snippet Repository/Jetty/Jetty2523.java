    public void generateQuickstartWebXml(String extraXML) throws Exception
    {
        Resource descriptor = getWebInf().addPath(QuickStartDescriptorGenerator.DEFAULT_QUICKSTART_DESCRIPTOR_NAME);
        if (!descriptor.exists())
            descriptor.getFile().createNewFile();
        QuickStartDescriptorGenerator generator = new QuickStartDescriptorGenerator(this, extraXML, _originAttribute, _generateOrigin);
        try (FileOutputStream fos = new FileOutputStream(descriptor.getFile()))
        {
            generator.generateQuickStartWebXml(fos);
        }
    } 
