    @Override
    public int compareChildNodePointers( NodePointer pointer1, NodePointer pointer2 )
    {
        Xpp3Dom node1 = (Xpp3Dom) pointer1.getBaseValue();
        Xpp3Dom node2 = (Xpp3Dom) pointer2.getBaseValue();
        if ( node1 == node2 )
        {
            return 0;
        }
        for ( int i = 0; i < node.getChildCount(); i++ )
        {
            Xpp3Dom child = node.getChild( i );
            if ( child == node1 )
            {
                return -1;
            }
            if ( child == node2 )
            {
                return 1;
            }
        }
        return 0;
    }
