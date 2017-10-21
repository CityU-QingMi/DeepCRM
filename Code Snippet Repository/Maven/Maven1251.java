    private boolean testNode( Xpp3Dom node )
    {
        if ( test == null )
        {
            return true;
        }
        if ( test instanceof NodeNameTest )
        {
            String nodeName = node.getName();
            if ( StringUtils.isEmpty( nodeName ) )
            {
                return false;
            }

            NodeNameTest nodeNameTest = (NodeNameTest) test;
            String namespaceURI = nodeNameTest.getNamespaceURI();
            boolean wildcard = nodeNameTest.isWildcard();
            String testName = nodeNameTest.getNodeName().getName();
            String testPrefix = nodeNameTest.getNodeName().getPrefix();
            if ( wildcard && testPrefix == null )
            {
                return true;
            }
            if ( wildcard || testName.equals( nodeName ) )
            {
                return StringUtils.isEmpty( namespaceURI ) || StringUtils.isEmpty( testPrefix );
            }
            return false;
        }
        if ( test instanceof NodeTypeTest )
        {
            switch ( ( (NodeTypeTest) test ).getNodeType() )
            {
                case Compiler.NODE_TYPE_NODE:
                    return true;
                case Compiler.NODE_TYPE_TEXT:
                    return node.getValue() != null;
                default:
                    return false;
            }
        }
        return false;
    }
