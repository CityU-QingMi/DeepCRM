    public void doStart()
    {
        try
        {
            TaskLog.logWithTimestamp("Starting web application "+this.getDescriptor());
            if (jettyEnvXml != null && jettyEnvXml.exists())
                envConfiguration.setJettyEnvXml(Resource.toURL(jettyEnvXml));
            
            ClassLoader parentLoader = this.getClass().getClassLoader();
            if (parentLoader instanceof AntClassLoader)
                parentLoader = new AntURLClassLoader((AntClassLoader)parentLoader);

            setClassLoader(new WebAppClassLoader(parentLoader, this));
            if (attributes != null && attributes.getAttributes() != null)
            {
                for (Attribute a:attributes.getAttributes())
                    setAttribute(a.getName(), a.getValue());
            }
            
            //apply a context xml file if one was supplied
            if (contextXml != null)
            {
                XmlConfiguration xmlConfiguration = new XmlConfiguration(Resource.toURL(contextXml));
                TaskLog.log("Applying context xml file "+contextXml);
                xmlConfiguration.configure(this);   
            }
            
            super.doStart();
        }
        catch (Exception e)
        {
            TaskLog.log(e.toString());
        }
    }
