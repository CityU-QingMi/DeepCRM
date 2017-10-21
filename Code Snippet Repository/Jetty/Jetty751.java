    private void loadProperties(String resource) throws FileNotFoundException, IOException
    {   
        Resource file=Resource.newResource(resource);
        if (file!=null && file.exists())
        {
            Properties properties = new Properties();
            properties.load(file.getInputStream());
            for (Map.Entry<Object, Object> entry : properties.entrySet())
                _map.put(entry.getKey().toString(),String.valueOf(entry.getValue()));
        }
    }
