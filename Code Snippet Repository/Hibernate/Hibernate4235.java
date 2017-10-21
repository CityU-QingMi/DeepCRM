	@Override
	public ProcedureCallImplementor<R> registerStoredProcedureParameter(int position, Class type, ParameterMode mode) {
		getProducer().checkOpen( true );

		try {
			registerParameter( position, type, mode );
		}
		catch (HibernateException he) {
			throw getExceptionConverter().convert( he );
		}
		catch (RuntimeException e) {
			getProducer().markForRollbackOnly();
			throw e;
		}

		return this;
	}
