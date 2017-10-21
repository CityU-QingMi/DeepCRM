    public Xpp3DomAttributeIterator( NodePointer parent, QName qname )
    {
        this.parent = parent;
        this.node = (Xpp3Dom) parent.getNode();

        Map<String, String> map = new LinkedHashMap<>();
        for ( String name : this.node.getAttributeNames() )
        {
            if ( name.equals( qname.getName() ) || "*".equals( qname.getName() ) )
            {
                String value = this.node.getAttribute( name );
                map.put( name, value );
            }
        }
        this.attributes = new ArrayList<>( map.entrySet() );
    }
