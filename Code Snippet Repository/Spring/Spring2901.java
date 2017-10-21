	@Test
	public void testHitMaxSizeLoadedFromContext() throws Exception {
		Advised person = (Advised) beanFactory.getBean("maxSizePooledPerson");
		CommonsPool2TargetSource targetSource = (CommonsPool2TargetSource) person.getTargetSource();

		int maxSize = targetSource.getMaxSize();
		Object[] pooledInstances = new Object[maxSize];

		for (int x = 0; x < maxSize; x++) {
			Object instance = targetSource.getTarget();
			assertNotNull(instance);
			pooledInstances[x] = instance;
		}

		// should be at maximum now
		try {
			targetSource.getTarget();
			fail("Should throw NoSuchElementException");
		}
		catch (NoSuchElementException ex) {
			// desired
		}

		// lets now release an object and try to accquire a new one
		targetSource.releaseTarget(pooledInstances[9]);
		pooledInstances[9] = targetSource.getTarget();

		// release all objects
		for (int i = 0; i < pooledInstances.length; i++) {
			System.out.println(i);
			targetSource.releaseTarget(pooledInstances[i]);
		}
	}
