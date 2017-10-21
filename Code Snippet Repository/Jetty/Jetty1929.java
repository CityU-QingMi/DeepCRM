    public void cleanupSrcs() throws Exception
    {
        // delete the .java files - depending on keepGenerated setting
        if (!keepSources)
        {
            File generatedClassesDir = new File(generatedClasses);

            if(generatedClassesDir.exists() && generatedClassesDir.isDirectory())
            {
                delete(generatedClassesDir, new FileFilter()
                {
                    public boolean accept(File f)
                    {
                        return f.isDirectory() || f.getName().endsWith(".java");
                    }                
                });
            }
        }
    }
