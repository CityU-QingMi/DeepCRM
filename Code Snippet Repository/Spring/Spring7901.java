	@Test
	@SuppressWarnings("")
	public void testConvertJpaPersistenceException() {
		EntityNotFoundException entityNotFound = new EntityNotFoundException();
		assertSame(JpaObjectRetrievalFailureException.class,
				EntityManagerFactoryUtils.convertJpaAccessExceptionIfPossible(entityNotFound).getClass());

		NoResultException noResult = new NoResultException();
		assertSame(EmptyResultDataAccessException.class,
				EntityManagerFactoryUtils.convertJpaAccessExceptionIfPossible(noResult).getClass());

		NonUniqueResultException nonUniqueResult = new NonUniqueResultException();
		assertSame(IncorrectResultSizeDataAccessException.class,
				EntityManagerFactoryUtils.convertJpaAccessExceptionIfPossible(nonUniqueResult).getClass());

		OptimisticLockException optimisticLock = new OptimisticLockException();
		assertSame(JpaOptimisticLockingFailureException.class,
				EntityManagerFactoryUtils.convertJpaAccessExceptionIfPossible(optimisticLock).getClass());

		EntityExistsException entityExists = new EntityExistsException("foo");
		assertSame(DataIntegrityViolationException.class,
				EntityManagerFactoryUtils.convertJpaAccessExceptionIfPossible(entityExists).getClass());

		TransactionRequiredException transactionRequired = new TransactionRequiredException("foo");
		assertSame(InvalidDataAccessApiUsageException.class,
				EntityManagerFactoryUtils.convertJpaAccessExceptionIfPossible(transactionRequired).getClass());

		PersistenceException unknown = new PersistenceException() {
		};
		assertSame(JpaSystemException.class,
				EntityManagerFactoryUtils.convertJpaAccessExceptionIfPossible(unknown).getClass());
	}
