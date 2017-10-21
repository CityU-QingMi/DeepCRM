    private static List<URL> getJettyConfigurationURLs(Bundle configurationBundle, Dictionary properties)
    throws Exception
    {
        List<URL> configURLs = new ArrayList<URL>();
        String files = System.getProperty(JETTY_ETC_FILES, DEFAULT_JETTY_ETC_FILES);       
        StringTokenizer tokenizer = new StringTokenizer(files, ";,", false);

        while (tokenizer.hasMoreTokens())
        {
            String etcFile = tokenizer.nextToken().trim();

            //file path is absolute
            if (etcFile.startsWith("/") || etcFile.indexOf(":") != -1)
                configURLs.add(new URL(etcFile));
            else //relative file path
            {
                Enumeration<URL> enUrls = BundleFileLocatorHelperFactory.getFactory().getHelper().findEntries(configurationBundle, etcFile);

                String home = null;
                // default for org.eclipse.osgi.boot where we look inside
                // jettyhome/ for the default embedded configuration.
                if ((enUrls == null || !enUrls.hasMoreElements()))
                {
                    home = DEFAULT_JETTYHOME;
                    String tmp = DEFAULT_JETTYHOME+(DEFAULT_JETTYHOME.endsWith("/")?"":"/")+etcFile;
                    enUrls = BundleFileLocatorHelperFactory.getFactory().getHelper().findEntries(configurationBundle, tmp);                    
                    LOG.info("Configuring jetty from bundle: {} with {}", configurationBundle.getSymbolicName(),tmp);
                }          

                //lazily ensure jetty.home value is set based on location of etc files
                if (properties.get(OSGiServerConstants.JETTY_HOME) == null)
                {
                    Resource res = findDir(configurationBundle, home);
                    if (res != null)
                        properties.put(OSGiServerConstants.JETTY_HOME, res.toString());
                }
         
                if (enUrls == null || !enUrls.hasMoreElements())
                    throw new IllegalStateException ("Unable to locate a jetty configuration file for " + etcFile);

                URL url = BundleFileLocatorHelperFactory.getFactory().getHelper().getFileURL(enUrls.nextElement());
                configURLs.add(url);
           
            }
        }
        return configURLs;
    }
