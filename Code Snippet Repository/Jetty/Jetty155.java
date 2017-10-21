    public List<File> getClassPathFiles()
    {
        List<File> classPathFiles = new ArrayList<File>();
        Iterator classesIterator = classes.iterator();
        while (classesIterator.hasNext())
        {
            FileSet clazz = (FileSet) classesIterator.next();
            classPathFiles.add(clazz.getDirectoryScanner(project).getBasedir());
        }

        Iterator iterator = libraries.iterator();
        while (iterator.hasNext())
        {
            FileSet library = (FileSet) iterator.next();
            String[] includedFiles = library.getDirectoryScanner(project).getIncludedFiles();
            File baseDir = library.getDirectoryScanner(project).getBasedir();

            for (int i = 0; i < includedFiles.length; i++)
            {
                classPathFiles.add(new File(baseDir, includedFiles[i]));
            }
        }


        return classPathFiles;
    }
