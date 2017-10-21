    private static String getBundleVersion(Manifest manifest)
    {
        Attributes attribs = manifest.getMainAttributes();
        if (attribs == null)
        {
            return null;
        }

        String version = attribs.getValue("Bundle-Version");
        if (version == null)
        {
            return null;
        }

        return stripV(version);
    }
