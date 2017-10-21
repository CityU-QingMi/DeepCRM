    private static JarEntry findEntry(JarFile jar, String regex)
    {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;
        Enumeration<JarEntry> en = jar.entries();
        while (en.hasMoreElements())
        {
            JarEntry entry = en.nextElement();
            matcher = pattern.matcher(entry.getName());
            if (matcher.matches())
            {
                return entry;
            }
        }

        return null;
    }
