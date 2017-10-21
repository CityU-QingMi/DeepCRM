    public static ClassLoader createTestClassLoader(ClassLoader parentClassLoader) throws MalformedURLException
    {
        File testJarDir = MavenTestingUtils.getTargetFile("test-jars");
        Assume.assumeTrue(testJarDir.exists()); // trigger @Ignore if dir not there

        File jarfiles[] = testJarDir.listFiles(new FileFilter()
        {
            public boolean accept(File path)
            {
                if (!path.isFile())
                {
                    return false;
                }
                return path.getName().endsWith(".jar");
            }
        });

        Assume.assumeTrue(jarfiles.length > 0); // trigger @Ignore if no jar files.

        URL urls[] = new URL[jarfiles.length];
        for (int i = 0; i < jarfiles.length; i++)
        {
            urls[i] = jarfiles[i].toURI().toURL();
            // System.out.println("Adding test-jar => " + urls[i]);
        }

        return new URLClassLoader(urls,parentClassLoader);
    }
