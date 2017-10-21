        @Override
        public boolean matches( String requirement )
        {
            try
            {
                VersionRange range = VersionRange.createFromVersionSpec( requirement );
                if ( range.hasRestrictions() )
                {
                    return range.containsVersion( version );
                }
                else
                {
                    return range.getRecommendedVersion().compareTo( version ) == 0;
                }
            }
            catch ( InvalidVersionSpecificationException ex )
            {
                //TODO error reporting
                ex.printStackTrace();
                return false;
            }
        }
