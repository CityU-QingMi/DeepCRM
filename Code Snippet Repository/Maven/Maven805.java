    private static String createMessage( Plugin plugin, String descriptorLocation, Throwable e )
    {
        String message = "Failed to parse plugin descriptor";

        if ( plugin != null )
        {
            message += " for " + plugin.getId();
        }

        if ( descriptorLocation != null )
        {
            message += " (" + descriptorLocation + ")";
        }

        if ( e != null )
        {
            message += ": " + e.getMessage();
        }

        return message;
    }
