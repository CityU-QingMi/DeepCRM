    public void disable()
    {
        active = false;
        if ( children != null )
        {
            for ( ResolutionNode node : children )
            {
                node.disable();
            }
        }
    }
