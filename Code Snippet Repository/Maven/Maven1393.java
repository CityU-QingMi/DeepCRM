    static String toPath( Model model )
    {
        String path = "";

        if ( model != null )
        {
            File pomFile = model.getPomFile();

            if ( pomFile != null )
            {
                path = pomFile.getAbsolutePath();
            }
        }

        return path;
    }
