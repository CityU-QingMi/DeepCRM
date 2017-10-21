	@Test
	public void testNamedStoredProcedureBinding() {
		EntityManager em = getOrCreateEntityManager();
		SessionFactoryImplementor sf = em.getEntityManagerFactory().unwrap( SessionFactoryImplementor.class );
		final ProcedureCallMementoImpl m1 = (ProcedureCallMementoImpl) sf.getNamedQueryRepository()
				.getNamedProcedureCallMemento( "s1" );
		assertNotNull( m1 );
		assertEquals( "p1", m1.getProcedureName() );
		assertEquals( ParameterStrategy.NAMED, m1.getParameterStrategy() );
		List<ProcedureCallMementoImpl.ParameterMemento> list = m1.getParameterDeclarations();
		assertEquals( 2, list.size() );
		ProcedureCallMementoImpl.ParameterMemento memento = list.get( 0 );
		assertEquals( "p11", memento.getName() );
		assertEquals( javax.persistence.ParameterMode.IN, memento.getMode() );
		assertEquals( IntegerType.INSTANCE, memento.getHibernateType() );
		assertEquals( Integer.class, memento.getType() );

		memento = list.get( 1 );
		assertEquals( "p12", memento.getName() );
		assertEquals( javax.persistence.ParameterMode.IN, memento.getMode() );
		assertEquals( IntegerType.INSTANCE, memento.getHibernateType() );
		assertEquals( Integer.class, memento.getType() );



		final ProcedureCallMementoImpl m2 = (ProcedureCallMementoImpl) sf.getNamedQueryRepository()
				.getNamedProcedureCallMemento( "s2" );
		assertNotNull( m2 );
		assertEquals( "p2", m2.getProcedureName() );
		assertEquals( ParameterStrategy.POSITIONAL, m2.getParameterStrategy() );
		list = m2.getParameterDeclarations();

		memento = list.get( 0 );
		assertEquals( Integer.valueOf( 1 ), memento.getPosition() );
		assertEquals( javax.persistence.ParameterMode.INOUT, memento.getMode() );
		assertEquals( StringType.INSTANCE, memento.getHibernateType() );
		assertEquals( String.class, memento.getType() );

		memento = list.get( 1 );
		assertEquals( Integer.valueOf( 2 ), memento.getPosition() );
		assertEquals( javax.persistence.ParameterMode.INOUT, memento.getMode() );
		assertEquals( LongType.INSTANCE, memento.getHibernateType() );
		assertEquals( Long.class, memento.getType() );

	}
