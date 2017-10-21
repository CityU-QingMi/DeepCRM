        @Override
        protected String extrapolateChildUrl( String parentUrl, Map<Object, Object> context )
        {
            Object childDirectory = context.get( CHILD_DIRECTORY );
            Object childPathAdjustment = context.get( CHILD_PATH_ADJUSTMENT );

            if ( StringUtils.isBlank( parentUrl ) || childDirectory == null || childPathAdjustment == null )
            {
                return parentUrl;
            }

            // append childPathAdjustment and childDirectory to parent url
            return appendPath( parentUrl, childDirectory.toString(), childPathAdjustment.toString() );
        }
