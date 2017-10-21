    public static boolean isWebArchive(File path)
    {
        if (path.isFile())
        {
            String name = path.getName().toLowerCase(Locale.ENGLISH);
            return (name.endsWith(".war") || name.endsWith(".jar"));
        }

        File webInf = new File(path,"WEB-INF");
        File webXml = new File(webInf,"web.xml");
        return webXml.exists() && webXml.isFile();
    }
