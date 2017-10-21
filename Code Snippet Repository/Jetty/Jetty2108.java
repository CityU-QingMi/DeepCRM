    private static List<String> getJarsInWebInfLib(JarFile jarFile)
    {
        List<String> res = new ArrayList<String>();
        Enumeration<JarEntry> en = jarFile.entries();
        while (en.hasMoreElements())
        {
            JarEntry e = en.nextElement();
            if (e.getName().startsWith("WEB-INF/lib/") && e.getName().endsWith(".jar"))
            {
                res.add(e.getName());
            }
        }
        return res;
    }
