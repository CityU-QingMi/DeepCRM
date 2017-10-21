    private List<String> parseStrings( Xpp3Dom dom )
    {
        List<String> strings = null;

        if ( dom != null )
        {
            strings = new ArrayList<>();

            for ( Xpp3Dom child : dom.getChildren() )
            {
                String string = child.getValue();
                if ( string != null )
                {
                    string = string.trim();
                    if ( string.length() > 0 )
                    {
                        strings.add( string );
                    }
                }
            }
        }

        return strings;
    }
