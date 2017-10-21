    private static Object getValue( Xpp3Dom node )
    {
        if ( node.getValue() != null )
        {
            return node.getValue();
        }
        else
        {
            List<Object> children = new ArrayList<>();
            for ( int i = 0; i < node.getChildCount(); i++ )
            {
                children.add( getValue( node.getChild( i ) ) );
            }
            return children;
        }
    }
