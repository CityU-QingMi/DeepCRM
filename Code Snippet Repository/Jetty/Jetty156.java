    public FileMatchingConfiguration getLibrariesConfiguration()
    {
        FileMatchingConfiguration config = new FileMatchingConfiguration();

        Iterator classesIterator = classes.iterator();
        while (classesIterator.hasNext())
        {
            FileSet clazz = (FileSet) classesIterator.next();
            config.addDirectoryScanner(clazz.getDirectoryScanner(project));
        }

        Iterator librariesIterator = libraries.iterator();
        while (librariesIterator.hasNext())
        {
            FileSet library = (FileSet) librariesIterator.next();
            config.addDirectoryScanner(library.getDirectoryScanner(project));
        }

        return config;
    }
