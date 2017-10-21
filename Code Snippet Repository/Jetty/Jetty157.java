    public void configure(WebAppContext context) throws Exception
    {
        if (context instanceof AntWebAppContext)
        {
            List<File> classPathFiles = ((AntWebAppContext)context).getClassPathFiles();
            if (classPathFiles != null)
            {
                for (File cpFile:classPathFiles)
                {
                    if (cpFile.exists())
                    {
                        ((WebAppClassLoader) context.getClassLoader()).addClassPath(cpFile.getCanonicalPath());
                    }
                }
            }
        }
        super.configure(context);
    }
