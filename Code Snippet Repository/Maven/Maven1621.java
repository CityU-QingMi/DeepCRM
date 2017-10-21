    protected void mergeCiManagement_Notifiers( CiManagement target, CiManagement source, boolean sourceDominant,
                                                Map<Object, Object> context )
    {
        List<Notifier> src = source.getNotifiers();
        if ( !src.isEmpty() )
        {
            List<Notifier> tgt = target.getNotifiers();
            Map<Object, Notifier> merged = new LinkedHashMap<>( ( src.size() + tgt.size() ) * 2 );

            for ( Notifier element : tgt )
            {
                Object key = getNotifierKey( element );
                merged.put( key, element );
            }

            for ( Notifier element : src )
            {
                Object key = getNotifierKey( element );
                if ( sourceDominant || !merged.containsKey( key ) )
                {
                    merged.put( key, element );
                }
            }

            target.setNotifiers( new ArrayList<>( merged.values() ) );
        }
    }
