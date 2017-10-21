    static void loadProperties(String resourceName, Properties props)
    {
        URL testProps = Loader.getResource(resourceName);
        if (testProps != null)
        {
            try (InputStream in = testProps.openStream())
            {
                Properties p = new Properties();
                p.load(in);
                for (Object key : p.keySet())
                {
                    Object value = p.get(key);
                    if (value != null)
                    {
                        props.put(key,value);
                    }
                }
            }
            catch (IOException e)
            {
                System.err.println("[WARN] Error loading logging config: " + testProps);
                e.printStackTrace(System.err);
            }
        }
    }
