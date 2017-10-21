    public File createFile( File dir, String filename, String contents, String encoding )
        throws IOException
    {
        File file = new File( dir, filename );

        file.getParentFile().mkdirs();

        FileUtils.fileWrite( file.getPath(), encoding, contents );

        markForDeletion( file );

        return file;
    }
