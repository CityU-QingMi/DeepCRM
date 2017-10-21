    private static String getMainManifestImplVersion(Manifest manifest)
    {
        Attributes attribs = manifest.getMainAttributes();
        if (attribs == null)
        {
            return null;
        }

        String version = attribs.getValue(Attributes.Name.IMPLEMENTATION_VERSION);
        if (version == null)
        {
            return null;
        }

        return stripV(version);
    }
