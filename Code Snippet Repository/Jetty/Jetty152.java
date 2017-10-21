    @Override
    public void setWar(String path)
    {
        super.setWar(path);

        try
        {
            Resource war = Resource.newResource(path);
            if (war.exists() && war.isDirectory() && getDescriptor() == null)
            {
                Resource webXml = war.addPath("WEB-INF/web.xml");
                setDescriptor(webXml.toString());
            }
        }
        catch (IOException e)
        {
            throw new BuildException(e);
        }
    }
