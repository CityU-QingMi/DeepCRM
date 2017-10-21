	public AbstractTransactionAspectTests() {
		try {
			// Cache the methods we'll be testing
			exceptionalMethod = ITestBean.class.getMethod("exceptional", new Class[] { Throwable.class });
			getNameMethod = ITestBean.class.getMethod("getName", (Class[]) null);
			setNameMethod = ITestBean.class.getMethod("setName", new Class[] { String.class} );
		}
		catch (NoSuchMethodException ex) {
			throw new RuntimeException("Shouldn't happen", ex);
		}
	}
