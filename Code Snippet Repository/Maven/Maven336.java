    public void cleanUp()
        throws IOException
    {
        for ( Iterator it = filesToDelete.iterator(); it.hasNext(); )
        {
            File file = (File) it.next();

            if ( file.exists() )
            {
                if ( file.isDirectory() )
                {
                    FileUtils.deleteDirectory( file );
                }
                else
                {
                    file.delete();
                }
            }

            it.remove();
        }

        warnAboutCleanup = false;
    }
