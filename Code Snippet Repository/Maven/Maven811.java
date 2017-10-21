    public String buildDiagnosticMessage()
    {
        StringBuilder messageBuffer = new StringBuilder( 256 );

        List<Parameter> params = getParameters();
        MojoDescriptor mojo = getMojoDescriptor();

        messageBuffer.append( "One or more required plugin parameters are invalid/missing for \'" )
            .append( mojo.getPluginDescriptor().getGoalPrefix() ).append( ':' ).append( mojo.getGoal() )
            .append( "\'\n" );

        int idx = 0;
        for ( Iterator<Parameter> it = params.iterator(); it.hasNext(); idx++ )
        {
            Parameter param = it.next();

            messageBuffer.append( "\n[" ).append( idx ).append( "] " );

            decomposeParameterIntoUserInstructions( mojo, param, messageBuffer );

            messageBuffer.append( '\n' );
        }

        return messageBuffer.toString();
    }
