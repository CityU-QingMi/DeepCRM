	@Test
	public void exceptionTranslationWithTranslation() {
		MapPersistenceExceptionTranslator mpet1 = new MapPersistenceExceptionTranslator();
		RuntimeException in1 = new RuntimeException("in");
		InvalidDataAccessApiUsageException out1 = new InvalidDataAccessApiUsageException("out");
		InvalidDataAccessApiUsageException out2 = new InvalidDataAccessApiUsageException("out");
		mpet1.addTranslation(in1, out1);

		ChainedPersistenceExceptionTranslator chainedPet1 = new ChainedPersistenceExceptionTranslator();
		assertSame("Should not translate yet", in1, DataAccessUtils.translateIfNecessary(in1, chainedPet1));
		chainedPet1.addDelegate(mpet1);
		assertSame("Should now translate", out1, DataAccessUtils.translateIfNecessary(in1, chainedPet1));

		// Now add a new translator and verify it wins
		MapPersistenceExceptionTranslator mpet2 = new MapPersistenceExceptionTranslator();
		mpet2.addTranslation(in1, out2);
		chainedPet1.addDelegate(mpet2);
		assertSame("Should still translate the same due to ordering",
				out1, DataAccessUtils.translateIfNecessary(in1, chainedPet1));

		ChainedPersistenceExceptionTranslator chainedPet2 = new ChainedPersistenceExceptionTranslator();
		chainedPet2.addDelegate(mpet2);
		chainedPet2.addDelegate(mpet1);
		assertSame("Should translate differently due to ordering",
				out2, DataAccessUtils.translateIfNecessary(in1, chainedPet2));

		RuntimeException in2 = new RuntimeException("in2");
		OptimisticLockingFailureException out3 = new OptimisticLockingFailureException("out2");
		assertNull(chainedPet2.translateExceptionIfPossible(in2));
		MapPersistenceExceptionTranslator mpet3 = new MapPersistenceExceptionTranslator();
		mpet3.addTranslation(in2, out3);
		chainedPet2.addDelegate(mpet3);
		assertSame(out3, chainedPet2.translateExceptionIfPossible(in2));
	}
