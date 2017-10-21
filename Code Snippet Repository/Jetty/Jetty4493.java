    public static String getVersion(File file)
    {
        try (JarFile jar = new JarFile(file))
        {
            String version = null;

            Manifest manifest = jar.getManifest();
            
            if (manifest == null)
            {
                return "(none specified)";
            }

            version = getMainManifestImplVersion(manifest);
            if (version != null)
            {
                return version;
            }

            version = getSubManifestImplVersion(manifest);
            if (version != null)
            {
                return version;
            }

            version = getBundleVersion(manifest);
            if (version != null)
            {
                return version;
            }

            version = getMavenVersion(jar);
            if (version != null)
            {
                return version;
            }

            return "(none specified)";
        }
        catch (IOException e)
        {
            return "(error: " + e.getClass().getSimpleName() + " " + e.getMessage() + ")";
        }
    }
