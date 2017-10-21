	private IllegalArgumentException testRejectsSwapToInvalidValue(Object invalid) {
		HotSwappableTargetSource swapper = (HotSwappableTargetSource) beanFactory.getBean("swapper");
		IllegalArgumentException aopex = null;
		try {
			swapper.swap(invalid);
			fail("Shouldn't be able to swap to invalid value [" + invalid + "]");
		}
		catch (IllegalArgumentException ex) {
			// Ok
			aopex = ex;
		}

		// It shouldn't be corrupted, it should still work
		testBasicFunctionality();
		return aopex;
	}
