        private void concatPath( StringBuilder url, String path )
        {
            if ( path.length() > 0 )
            {
                boolean initialUrlEndsWithSlash = url.charAt( url.length() - 1 ) == '/';
                boolean pathStartsWithSlash = path.charAt( 0 ) ==  '/';

                if ( pathStartsWithSlash )
                {
                    if ( initialUrlEndsWithSlash )
                    {
                        // 1 extra '/' to remove
                        url.setLength( url.length() - 1 );
                    }
                }
                else if ( !initialUrlEndsWithSlash )
                {
                    // add missing '/' between url and path
                    url.append( '/' );
                }

                url.append( path );

                // ensure resulting url ends with slash if initial url was
                if ( initialUrlEndsWithSlash && !path.endsWith( "/" ) )
                {
                    url.append( '/' );
                }
            }
        }
