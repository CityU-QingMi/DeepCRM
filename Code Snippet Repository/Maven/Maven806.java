    private static String constructMessage( MojoExecution mojoExecution, Throwable cause )
    {
        String message;

        if ( mojoExecution != null )
        {
            message =
                "Execution " + mojoExecution.getExecutionId() + " of goal " + mojoExecution.getMojoDescriptor().getId()
                    + " failed";
        }
        else
        {
            message = "Mojo execution failed";
        }

        if ( cause != null && StringUtils.isNotEmpty( cause.getMessage() ) )
        {
            message += ": " + cause.getMessage();
        }
        else
        {
            message += ".";
        }

        return message;
    }
