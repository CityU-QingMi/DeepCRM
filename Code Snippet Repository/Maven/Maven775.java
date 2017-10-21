        public void merge( Model target, Model source )
        {
            if ( target.getBuild() == null )
            {
                target.setBuild( new Build() );
            }

            Map<Object, Object> context =
                Collections.<Object, Object>singletonMap( PLUGIN_MANAGEMENT, target.getBuild().getPluginManagement() );

            mergePluginContainer_Plugins( target.getBuild(), source.getBuild(), false, context );
        }
