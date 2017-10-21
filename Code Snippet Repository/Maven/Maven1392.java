    static String toSourceHint( Model model )
    {
        if ( model == null )
        {
            return "";
        }

        StringBuilder buffer = new StringBuilder( 192 );

        buffer.append( toId( model ) );

        File pomFile = model.getPomFile();
        if ( pomFile != null )
        {
            buffer.append( " (" ).append( pomFile ).append( ')' );
        }

        return buffer.toString();
    }
