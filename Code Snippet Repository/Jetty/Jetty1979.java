    @Override
    protected void loadConfigurations() throws Exception
    {
        super.loadConfigurations();
        
        //inject configurations with config from maven plugin    
        for (Configuration c:getConfigurations())
        {
            if (c instanceof EnvConfiguration && getJettyEnvXml() != null)
                ((EnvConfiguration)c).setJettyEnvXml(Resource.toURL(new File(getJettyEnvXml())));
            else if (c instanceof MavenQuickStartConfiguration && getQuickStartWebDescriptor() != null)
                ((MavenQuickStartConfiguration)c).setQuickStartWebXml(getQuickStartWebDescriptor());         
        }
    }
