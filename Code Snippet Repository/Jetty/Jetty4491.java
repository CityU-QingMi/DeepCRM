    private static String getMavenVersion(JarFile jar) throws IOException
    {
        JarEntry pomProp = findEntry(jar,"META-INF/maven/.*/pom\\.properties$");
        if (pomProp == null)
        {
            return null;
        }

        InputStream stream = null;

        try
        {
            stream = jar.getInputStream(pomProp);
            Properties props = new Properties();
            props.load(stream);

            String version = props.getProperty("version");
            if (version == null)
            {
                return null;
            }

            return stripV(version);
        }
        finally
        {
            FS.close(stream);
        }
    }
