    private void deleteAllFiles(final File storeDir, final String idInContext)
    {
        File[] files = storeDir.listFiles (new FilenameFilter() {

/**/
/**/
/**/
            @Override
            public boolean accept(File dir, String name)
            {
                if (dir != storeDir)
                    return false;
                return (name.contains(idInContext));
            }
            
        });
        
        //no files for that id
        if (files == null || files.length < 1)
            return;
        
        //delete all files
        for (File f:files)
        {
           try
           {
               Files.deleteIfExists(f.toPath());
           }
           catch (Exception e)
           {
               LOG.warn("Unable to delete session file", e);
           }
        }
    }
