    private static List<URL> getJettyConfigurationURLs(File jettyhome) 
    throws MalformedURLException
    {
        List<URL> configURLs = new ArrayList<URL>();
        String jettyetc = System.getProperty(JETTY_ETC_FILES, DEFAULT_JETTY_ETC_FILES);
        StringTokenizer tokenizer = new StringTokenizer(jettyetc, ";,", false);
        while (tokenizer.hasMoreTokens())
        {
            String next = tokenizer.nextToken().trim();
            //etc files can either be relative to jetty.home or absolute disk locations
            if (!next.startsWith("/") && (next.indexOf(':') == -1))    
                configURLs.add(new File(jettyhome, next).toURI().toURL());
            else 
                configURLs.add(new URL(next));
        }
        return configURLs;
    }
