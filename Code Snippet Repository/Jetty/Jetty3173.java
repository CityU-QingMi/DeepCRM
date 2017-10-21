    private File getFile (final File storeDir, final String id)
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
                return (name.contains(getIdWithContext(id)));
            }
            
        });
        
        if (files == null || files.length < 1)
            return null;
        return files[0];
    }
