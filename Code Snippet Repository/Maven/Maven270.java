    private static int countNodes( MetadataTreeNode tree )
    {
        if ( tree == null )
        {
            return 0;
        }

        int count = 1;
        MetadataTreeNode[] kids = tree.getChildren();
        if ( kids == null || kids.length < 1 )
        {
            return count;
        }
        for ( MetadataTreeNode n : kids )
        {
            count += countNodes( n );
        }

        return count;
    }
