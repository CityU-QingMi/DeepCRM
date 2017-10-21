		@Override
        public void onLoad(LoadEvent event, LoadType loadType) throws HibernateException {
			if ( LoadEventListener.INTERNAL_LOAD_EAGER.getName().equals( loadType.getName() ) ) {
				internalLoadCount++;
				if ( internalLoadCount > 10 ) {
					throw new OverflowCondition();
				}
			}
			super.onLoad( event, loadType );
			internalLoadCount--;
		}
