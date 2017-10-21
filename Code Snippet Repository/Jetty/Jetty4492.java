    private static String getSubManifestImplVersion(Manifest manifest)
    {
        Map<String, Attributes> entries = manifest.getEntries();

        for (Attributes attribs : entries.values())
        {
            if (attribs == null)
            {
                continue; // skip entry
            }

            String version = attribs.getValue(Attributes.Name.IMPLEMENTATION_VERSION);
            if (version == null)
            {
                continue; // empty, no value, skip it
            }

            return stripV(version);
        }

        return null; // no valid impl version entries found
    }
