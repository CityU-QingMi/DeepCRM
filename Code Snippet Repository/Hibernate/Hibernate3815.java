	@SuppressWarnings("")
	public <T extends Return> T extractRootReturn(LoadPlan loadPlan, Class<T> returnType) {
		if ( loadPlan.getReturns().size() == 0 ) {
			throw new IllegalStateException( "LoadPlan contained no root returns" );
		}
		else if ( loadPlan.getReturns().size() > 1 ) {
			throw new IllegalStateException( "LoadPlan contained more than one root returns" );
		}

		final Return rootReturn = loadPlan.getReturns().get( 0 );
		if ( !returnType.isInstance( rootReturn ) ) {
			throw new IllegalStateException(
					String.format(
							"Unexpected LoadPlan root return; expecting %s, but found %s",
							returnType.getName(),
							rootReturn.getClass().getName()
					)
			);
		}

		return (T) rootReturn;
	}
