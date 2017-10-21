	@Override
	protected void addRootReturn(final Return rootReturn) {
		if ( this.rootEntityReturn != null ) {
			throw new HibernateException( "Root return already identified" );
		}
		if ( !( rootReturn instanceof EntityReturn ) ) {
			throw new HibernateException( "Load entity graph only supports EntityReturn" );
		}
		this.rootEntityReturn = (EntityReturn) rootReturn;
	}
