    public void store(OutputStream stream, String comments) throws IOException
    {
        Properties props = new Properties();
        // add all Props as normal properties, with expansion performed.
        for (Prop prop : this)
        {
            props.setProperty(prop.key,expand(prop.value));
        }
        // write normal properties file
        props.store(stream,comments);
    }
