	private void doTestProxyValidation(MyValidInterface proxy) {
		assertNotNull(proxy.myValidMethod("value", 5));
		try {
			assertNotNull(proxy.myValidMethod("value", 15));
			fail("Should have thrown ValidationException");
		}
		catch (javax.validation.ValidationException ex) {
			// expected
		}
		try {
			assertNotNull(proxy.myValidMethod(null, 5));
			fail("Should have thrown ValidationException");
		}
		catch (javax.validation.ValidationException ex) {
			// expected
		}
		try {
			assertNotNull(proxy.myValidMethod("value", 0));
			fail("Should have thrown ValidationException");
		}
		catch (javax.validation.ValidationException ex) {
			// expected
		}

		proxy.myValidAsyncMethod("value", 5);
		try {
			proxy.myValidAsyncMethod("value", 15);
			fail("Should have thrown ValidationException");
		}
		catch (javax.validation.ValidationException ex) {
			// expected
		}
		try {
			proxy.myValidAsyncMethod(null, 5);
			fail("Should have thrown ValidationException");
		}
		catch (javax.validation.ValidationException ex) {
			// expected
		}

		assertEquals("myValue", proxy.myGenericMethod("myValue"));
		try {
			proxy.myGenericMethod(null);
			fail("Should have thrown ValidationException");
		}
		catch (javax.validation.ValidationException ex) {
			// expected
		}
	}
