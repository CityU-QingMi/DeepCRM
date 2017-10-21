    public void enable()
    {
        active = true;

        // TODO if it was null, we really need to go find them now... or is this taken care of by the ordering?
        if ( children != null )
        {
            for ( ResolutionNode node : children )
            {
                node.enable();
            }
        }
    }
