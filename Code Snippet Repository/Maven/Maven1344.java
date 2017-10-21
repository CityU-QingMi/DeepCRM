    public String getOptionsAsHtml()
    {
        StringBuilder sb = new StringBuilder( 512 );
        boolean a = true;
        sb.append( "<table border='1' class='zebra-striped'><tr class='a'><th><b>Options</b></th><th><b>Description</b></th></tr>" );
        for ( Option option : new CLIManagerExtension().getOptions() )
        {
            a = !a;
            sb.append( "<tr class='" ).append( a ? 'a' : 'b' ).append( "'><td><code>-<a name='" );
            sb.append( option.getOpt() );
            sb.append( "'>" );
            sb.append( option.getOpt() );
            sb.append( "</a>,--<a name='" );
            sb.append( option.getLongOpt() );
            sb.append( "'>" );
            sb.append( option.getLongOpt() );
            sb.append( "</a>" );
            if ( option.hasArg() )
            {
                if ( option.hasArgName() )
                {
                    sb.append( " &lt;" ).append( option.getArgName() ).append( "&gt;" );
                }
                else
                {
                    sb.append( ' ' );
                }
            }
            sb.append( "</code></td><td>" );
            sb.append( option.getDescription() );
            sb.append( "</td></tr>" );
            sb.append( LS );
        }
        sb.append( "</table>" );
        return sb.toString();
    }
