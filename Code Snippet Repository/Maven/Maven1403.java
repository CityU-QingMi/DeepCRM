        private String appendPath( String parentUrl, String childPath, String pathAdjustment )
        {
            StringBuilder url = new StringBuilder( parentUrl.length() + pathAdjustment.length() + childPath.length()
                + ( ( pathAdjustment.length() == 0 ) ? 1 : 2 ) );

            url.append( parentUrl );
            concatPath( url, pathAdjustment );
            concatPath( url, childPath );

            return url.toString();
        }
