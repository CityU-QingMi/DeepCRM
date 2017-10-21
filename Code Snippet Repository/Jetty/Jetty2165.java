    private boolean isAcceptableLibrary(File file, Set<String> pathToClassFiles)
    {
        try
        {
            if (file.isDirectory())
            {
                for (String criteria : pathToClassFiles)
                {
                    if (new File(file, criteria).exists()) { return false; }
                }
            }
            else
            {
                JarFile jar = null;
                try
                {
                    jar = new JarFile(file);
                    for (String criteria : pathToClassFiles)
                    {
                        if (jar.getEntry(criteria) != null) { return false; }
                    }
                }
                finally
                {
                    if (jar != null) try
                    {
                        jar.close();
                    }
                    catch (IOException ioe)
                    {
                    }
                }
            }
        }
        catch (IOException e)
        {
            // nevermind. just trying our best
            __logger.ignore(e);
        }
        return true;
    }
