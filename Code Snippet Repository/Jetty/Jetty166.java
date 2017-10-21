    private void applyJettyXml()
    {
        if (jettyXml != null && jettyXml.exists())
        {
            TaskLog.log("Configuring jetty from xml configuration file = "
                    + jettyXml.getAbsolutePath());
            XmlConfiguration configuration;
            try
            {
                configuration = new XmlConfiguration(Resource.toURL(jettyXml));
                configuration.configure(server);
            }
            catch (MalformedURLException e)
            {
                throw new RuntimeException(e);
            }
            catch (SAXException e)
            {
                throw new RuntimeException(e);
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }
    }
