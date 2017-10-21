	@Test
	public void referenceIdentityByDefault() throws Exception {
		CommonsPool2TargetSource targetSource = new CommonsPool2TargetSource();
		targetSource.setMaxWait(1);
		prepareTargetSource(targetSource);

		Object first = targetSource.getTarget();
		Object second = targetSource.getTarget();
		assertTrue(first instanceof SerializablePerson);
		assertTrue(second instanceof SerializablePerson);
		assertEquals(first, second);

		targetSource.releaseTarget(first);
		targetSource.releaseTarget(second);
	}
