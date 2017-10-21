    public Xpp3DomNodeIterator( NodePointer parent, NodeTest test, boolean reverse, NodePointer startWith )
    {
        this.parent = parent;
        this.node = (Xpp3Dom) parent.getNode();
        this.children = this.node.getChildren();
        if ( startWith != null )
        {
            Xpp3Dom startWithNode = (Xpp3Dom) startWith.getNode();
            for ( ; filteredIndex < children.length; filteredIndex++ )
            {
                if ( startWithNode.equals( children[filteredIndex] ) )
                {
                    filteredIndex++;
                    break;
                }
            }
        }
        this.test = test;
        if ( reverse )
        {
            throw new UnsupportedOperationException();
        }
    }
