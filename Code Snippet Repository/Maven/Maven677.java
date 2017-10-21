    private static String createMessage( MojoExecution execution, MavenProject project, Throwable cause )
    {
        MessageBuilder buffer = buffer( 256 );

        buffer.a( "Failed to execute goal" );

        if ( execution != null )
        {
            buffer.a( ' ' );
            buffer.mojo( execution.getGroupId() + ':' + execution.getArtifactId() + ':' + execution.getVersion() + ':'
                + execution.getGoal() );
            buffer.a( ' ' ).strong( '(' + execution.getExecutionId() + ')' );
        }

        if ( project != null )
        {
            buffer.a( " on project " );
            buffer.project( project.getArtifactId() );
        }

        if ( cause != null )
        {
            buffer.a( ": " ).failure( cause.getMessage() );
        }

        return buffer.toString();
    }
