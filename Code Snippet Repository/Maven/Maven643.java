    public void setFailureBehavior( String failureBehavior )
    {
        if ( failureBehavior == null )
        {
            this.failureBehavior = FAIL_FAST; // default
            return;
        }
        if ( FAIL_FAST.equals( failureBehavior ) || FAIL_AT_END.equals( failureBehavior ) || FAIL_NEVER.equals(
            failureBehavior ) )
        {
            this.failureBehavior = failureBehavior;
        }
        else
        {
            throw new IllegalArgumentException(
                "Invalid failure behavior (must be one of: \'" + FAIL_FAST + "\', \'" + FAIL_AT_END + "\', \'"
                    + FAIL_NEVER + "\')." );
        }
    }
