        void normalize()
        {
            for ( int i = size() - 1; i >= 0; i-- )
            {
                Item lastItem = get( i );

                if ( lastItem.isNull() )
                {
                    // remove null trailing items: 0, "", empty list
                    remove( i );
                }
                else if ( !( lastItem instanceof ListItem ) )
                {
                    break;
                }
            }
        }
