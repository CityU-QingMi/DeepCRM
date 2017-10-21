    private void appendForkInfo( MessageBuilder buffer, MojoDescriptor md )
    {
        StringBuilder buff = new StringBuilder();
        if ( StringUtils.isNotEmpty( md.getExecutePhase() ) )
        {
            // forked phase
            if ( StringUtils.isNotEmpty( md.getExecuteLifecycle() ) )
            {
                buff.append( '[' );
                buff.append( md.getExecuteLifecycle() );
                buff.append( ']' );
            }
            buff.append( md.getExecutePhase() );
        }
        else
        {
            // forked goal
            buff.append( ':' );
            buff.append( md.getExecuteGoal() );
        }
        buffer.strong( buff.toString() );
    }
