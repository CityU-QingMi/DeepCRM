    @Override
    public void configure (WebAppContext context) throws Exception
    {
        //cannot configure if the _context is already started
        if (context.isStarted())
        {
            LOG.debug("Cannot configure webapp after it is started");
            return;
        }

        LOG.debug("Configuring web-jetty.xml");

        Resource web_inf = context.getWebInf();
        // handle any WEB-INF descriptors
        if(web_inf!=null&&web_inf.isDirectory())
        {
            // do jetty.xml file
            Resource jetty=web_inf.addPath("jetty8-web.xml");
            if(!jetty.exists())
                jetty=web_inf.addPath(JETTY_WEB_XML);
            if(!jetty.exists())
                jetty=web_inf.addPath("web-jetty.xml");

            if(jetty.exists())
            {             
                if(LOG.isDebugEnabled())
                    LOG.debug("Configure: "+jetty);

                Object xml_attr=context.getAttribute(XML_CONFIGURATION);
                context.removeAttribute(XML_CONFIGURATION);
                final XmlConfiguration jetty_config = xml_attr instanceof XmlConfiguration?(XmlConfiguration)xml_attr:new XmlConfiguration(jetty.getURI().toURL());

                setupXmlConfiguration(context, jetty_config, web_inf);

                try
                {
                    WebAppClassLoader.runWithServerClassAccess(()->{jetty_config.configure(context);return null;});
                }
                catch(Exception e)
                {
                    LOG.warn("Error applying {}",jetty);
                    throw e;
                }
            }
        }
    }
