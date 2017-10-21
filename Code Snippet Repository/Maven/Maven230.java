    public String getName()
    {
        String name = resource.getName();

        if ( name == null )
        {
            name = "";
        }
        else if ( name.startsWith( "/" ) )
        {
            name = name.substring( 1 );
        }

        return name;
    }
