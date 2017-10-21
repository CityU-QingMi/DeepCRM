    public static boolean isWebArchiveFile(File path)
    {
        if (!path.isFile())
        {
            return false;
        }

        String name = path.getName().toLowerCase(Locale.ENGLISH);
        return (name.endsWith(".war") || name.endsWith(".jar"));
    }
