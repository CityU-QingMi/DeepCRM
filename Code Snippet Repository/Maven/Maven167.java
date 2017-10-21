    private static String resolvePath( String uncleanPath )
    {
        LinkedList<String> pathElements = new LinkedList<>();

        StringTokenizer tokenizer = new StringTokenizer( uncleanPath, "/" );

        while ( tokenizer.hasMoreTokens() )
        {
            String token = tokenizer.nextToken();

            switch ( token )
            {
                case "":
                    // Empty path entry ("...//.."), remove.
                    break;
                case "..":
                    if ( pathElements.isEmpty() )
                    {
                        // FIXME: somehow report to the user
                        // that there are too many '..' elements.
                        // For now, ignore the extra '..'.
                    }
                    else
                    {
                        pathElements.removeLast();
                    }
                    break;
                default:
                    pathElements.addLast( token );
                    break;
            }
        }

        StringBuilder cleanedPath = new StringBuilder();

        while ( !pathElements.isEmpty() )
        {
            cleanedPath.append( pathElements.removeFirst() );
            if ( !pathElements.isEmpty() )
            {
                cleanedPath.append( '/' );
            }
        }

        return cleanedPath.toString();
    }
