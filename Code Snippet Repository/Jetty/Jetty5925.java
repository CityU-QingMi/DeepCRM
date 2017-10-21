    public Collection<URL> getTlds (URI uri) throws IOException
    {
        HashSet<URL> tlds = new HashSet<URL>();

        String jarUri = uriJarPrefix(uri, "!/");
        URL url = new URL(jarUri);
        JarURLConnection jarConn = (JarURLConnection) url.openConnection();
        jarConn.setUseCaches(Resource.getDefaultUseCaches());
        JarFile jarFile = jarConn.getJarFile();
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements())
        {
            JarEntry e = entries.nextElement();
            String name = e.getName();
            if (name.startsWith("META-INF") && name.endsWith(".tld"))
            {
                tlds.add(new URL(jarUri + name));
            }
        }
        if (!Resource.getDefaultUseCaches())
            jarFile.close();
        return tlds;
    }
