	private void assertOptimizer(
			SessionFactoryImplementor sessionFactory,
			Class<?> entityClass,
			Class<? extends Optimizer> expectedOptimizerClass) {
		assertTrue(
				SequenceStyleGenerator.class.isInstance(
						sessionFactory.getMetamodel()
								.entityPersister( entityClass )
								.getIdentifierGenerator()
				)
		);
		SequenceStyleGenerator generator = (SequenceStyleGenerator) sessionFactory.getMetamodel()
				.entityPersister( entityClass )
				.getIdentifierGenerator();
		assertTrue( expectedOptimizerClass.isInstance( generator.getOptimizer() ) );
	}
