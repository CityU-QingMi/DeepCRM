    @Override
    protected void startWebapp() throws Exception
    {
        if (isGenerateQuickStart())
        {
            if (getQuickStartWebDescriptor() == null)
                throw new IllegalStateException ("No location to generate quickstart descriptor");

            QuickStartDescriptorGenerator generator = new QuickStartDescriptorGenerator(this, _preconfigProcessor.getXML(), _originAttribute, _generateOrigin);
            try (FileOutputStream fos = new FileOutputStream(getQuickStartWebDescriptor().getFile()))
            {
                generator.generateQuickStartWebXml(fos);
            }
        }
        else
        {
            if (LOG.isDebugEnabled()) { LOG.debug("Calling full start on webapp");}
            super.startWebapp();
        }
    }
