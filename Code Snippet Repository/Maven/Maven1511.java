    private static InputLocation getLocation( String fieldName, InputLocationTracker tracker )
    {
        InputLocation location = null;

        if ( tracker != null )
        {
            if ( fieldName != null )
            {
                Object key = fieldName;

                int idx = fieldName.lastIndexOf( '.' );
                if ( idx >= 0 )
                {
                    fieldName = fieldName.substring( idx + 1 );
                    key = fieldName;
                }

                if ( fieldName.endsWith( "]" ) )
                {
                    key = fieldName.substring( fieldName.lastIndexOf( '[' ) + 1, fieldName.length() - 1 );
                    try
                    {
                        key = Integer.valueOf( key.toString() );
                    }
                    catch ( NumberFormatException e )
                    {
                        // use key as is
                    }
                }

                location = tracker.getLocation( key );
            }

            if ( location == null )
            {
                location = tracker.getLocation( "" );
            }
        }

        return location;
    }
