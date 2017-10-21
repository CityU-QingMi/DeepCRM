    private void filterChildren( int position )
    {
        for ( ; position > filteredChildren.size() && filteredIndex < children.length; filteredIndex++ )
        {
            Xpp3Dom child = children[filteredIndex];
            if ( testNode( child ) )
            {
                filteredChildren.add( child );
            }
        }
    }
