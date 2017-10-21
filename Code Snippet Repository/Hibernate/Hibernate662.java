	@Override
	public void addAnyMetaDef(AnyMetaDef defAnn) {
		if ( anyMetaDefs == null ) {
			anyMetaDefs = new HashMap<String, AnyMetaDef>();
		}
		else {
			if ( anyMetaDefs.containsKey( defAnn.name() ) ) {
				throw new AnnotationException( "Two @AnyMetaDef with the same name defined: " + defAnn.name() );
			}
		}

		anyMetaDefs.put( defAnn.name(), defAnn );
	}
