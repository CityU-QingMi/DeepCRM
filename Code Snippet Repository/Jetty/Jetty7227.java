    private void setConfig(XmlParser.Node config)
    {
        if ("Configure".equals(config.getTag()))
        {
            _processor=new JettyXmlConfiguration();
        }
        else if (__factoryLoader!=null)
        {
            for (ConfigurationProcessorFactory factory : __factoryLoader)
            {
                _processor = factory.getConfigurationProcessor(_dtd, config.getTag());
                if (_processor!=null)
                    break;
            }

            if (_processor==null)
                throw new IllegalStateException("Unknown configuration type: "+config.getTag()+" in "+this);
        }
        else
        {
            throw new IllegalArgumentException("Unknown XML tag:"+config.getTag());
        }
        _processor.init(_url,config,this);
    }
