    public static boolean isXmlFile(File path)
    {
        if (!path.isFile())
        {
            return false;
        }

        String name = path.getName().toLowerCase(Locale.ENGLISH);
        return name.endsWith(".xml");
    }
