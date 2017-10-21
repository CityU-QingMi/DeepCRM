	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		final Serializable context = generationContextLocator.locateGenerationContext( session, object );

		for ( Object generationPlan : generationPlans ) {
			final GenerationPlan plan = (GenerationPlan) generationPlan;
			plan.execute( session, object, context );
		}

		return context;
	}
