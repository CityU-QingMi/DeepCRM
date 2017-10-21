	private Dialect constructDialect(Object dialectReference) {
		final Dialect dialect;
		try {
			dialect = strategySelector.resolveStrategy( Dialect.class, dialectReference );
			if ( dialect == null ) {
				throw new HibernateException( "Unable to construct requested dialect [" + dialectReference + "]" );
			}
			return dialect;
		}
		catch (HibernateException e) {
			throw e;
		}
		catch (Exception e) {
			throw new HibernateException( "Unable to construct requested dialect [" + dialectReference + "]", e );
		}
	}
