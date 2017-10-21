    private static File findTool( String toolName, File installFolder )
    {
        File bin = new File( installFolder, "bin" ); //NOI18N
        if ( bin.exists() )
        {
            File tool = new File( bin, toolName + ( Os.isFamily( "windows" ) ? ".exe" : "" ) ); // NOI18N
            if ( tool.exists() )
            {
                return tool;
            }
        }
        return null;
   }
