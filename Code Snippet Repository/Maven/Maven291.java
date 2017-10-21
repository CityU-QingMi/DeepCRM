    public void addChild( int index, MetadataTreeNode kid )
    {
        if ( kid == null )
        {
            return;
        }

        if ( children == null )
        {
            children = new MetadataTreeNode[nChildren];
        }

        children[index % nChildren] = kid;
    }
