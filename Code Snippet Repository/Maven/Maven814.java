    @Override
    public File alignToBaseDirectory( File file )
    {
        // TODO Copied from the DefaultInterpolator. We likely want to resurrect the PathTranslator or at least a
        // similar component for re-usage
        if ( file != null )
        {
            if ( file.isAbsolute() )
            {
                // path was already absolute, just normalize file separator and we're done
            }
            else if ( file.getPath().startsWith( File.separator ) )
            {
                // drive-relative Windows path, don't align with project directory but with drive root
                file = file.getAbsoluteFile();
            }
            else
            {
                // an ordinary relative path, align with project directory
                file = new File( new File( basedir, file.getPath() ).toURI().normalize() ).getAbsoluteFile();
            }
        }
        return file;
    }
