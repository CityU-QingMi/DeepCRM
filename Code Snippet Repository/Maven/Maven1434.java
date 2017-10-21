        @Override
        protected void mergeDependency_Exclusions( Dependency target, Dependency source, boolean sourceDominant,
                                                   Map<Object, Object> context )
        {
            List<Exclusion> tgt = target.getExclusions();
            if ( tgt.isEmpty() )
            {
                List<Exclusion> src = source.getExclusions();

                for ( Exclusion element : src )
                {
                    Exclusion clone = element.clone();
                    target.addExclusion( clone );
                }
            }
        }
