	@SuppressWarnings("")
	public <T extends QuerySpace> T extractRootQuerySpace(QuerySpaces querySpaces, Class<EntityQuerySpace> returnType) {
		if ( querySpaces.getRootQuerySpaces().size() == 0 ) {
			throw new IllegalStateException( "LoadPlan contained no root query-spaces" );
		}
		else if ( querySpaces.getRootQuerySpaces().size() > 1 ) {
			throw new IllegalStateException( "LoadPlan contained more than one root query-space" );
		}

		final QuerySpace querySpace = querySpaces.getRootQuerySpaces().get( 0 );
		if ( !returnType.isInstance( querySpace ) ) {
			throw new IllegalStateException(
					String.format(
							"Unexpected LoadPlan root query-space; expecting %s, but found %s",
							returnType.getName(),
							querySpace.getClass().getName()
					)
			);
		}

		return (T) querySpace;
	}
