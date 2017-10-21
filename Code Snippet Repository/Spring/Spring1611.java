	public void xtestTypeMismatch() {
		try {
			getBeanFactory().getBean("typeMismatch");
			fail("Shouldn't succeed with type mismatch");
		}
		catch (BeanCreationException wex) {
			assertEquals("typeMismatch", wex.getBeanName());
			assertTrue(wex.getCause() instanceof PropertyBatchUpdateException);
			PropertyBatchUpdateException ex = (PropertyBatchUpdateException) wex.getCause();
			// Further tests
			assertTrue("Has one error ", ex.getExceptionCount() == 1);
			assertTrue("Error is for field age", ex.getPropertyAccessException("age") != null);
			assertTrue("We have rejected age in exception", ex.getPropertyAccessException("age").getPropertyChangeEvent().getNewValue().equals("34x"));
		}
	}
