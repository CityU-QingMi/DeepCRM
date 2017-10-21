    protected void mergeBuild_ScriptSourceDirectory( Build target, Build source, boolean sourceDominant,
                                                     Map<Object, Object> context )
    {
        String src = source.getScriptSourceDirectory();
        if ( src != null )
        {
            if ( sourceDominant || target.getScriptSourceDirectory() == null )
            {
                target.setScriptSourceDirectory( src );
                target.setLocation( "scriptSourceDirectory", source.getLocation( "scriptSourceDirectory" ) );
            }
        }
    }
