    private static String toMessage( String goal, PluginDescriptor pluginDescriptor )
    {
        StringBuilder buffer = new StringBuilder( 256 );

        buffer.append( "Could not find goal '" ).append( goal ).append( '\'' );

        if ( pluginDescriptor != null )
        {
            buffer.append( " in plugin " ).append( pluginDescriptor.getId() );

            buffer.append( " among available goals " );
            List<MojoDescriptor> mojos = pluginDescriptor.getMojos();
            if ( mojos != null )
            {
                for ( Iterator<MojoDescriptor> it = mojos.iterator(); it.hasNext(); )
                {
                    MojoDescriptor mojo = it.next();
                    if ( mojo != null )
                    {
                        buffer.append( mojo.getGoal() );
                    }
                    if ( it.hasNext() )
                    {
                        buffer.append( ", " );
                    }
                }
            }
        }

        return buffer.toString();
    }
