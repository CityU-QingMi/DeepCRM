	protected void doPostInstantiate() {
		if ( hasSequentialSelects ) {
			String[] entityNames = getSubclassClosure();
			for ( int i = 1; i < entityNames.length; i++ ) {
				Loadable loadable = (Loadable) getFactory().getEntityPersister( entityNames[i] );
				if ( !loadable.isAbstract() ) { //perhaps not really necessary...
					String sequentialSelect = generateSequentialSelect( loadable );
					sequentialSelectStringsByEntityName.put( entityNames[i], sequentialSelect );
				}
			}
		}
	}
