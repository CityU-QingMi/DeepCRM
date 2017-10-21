	@Test
	public void testFindCustomCodes() {
		class TestSQLErrorCodesFactory extends SQLErrorCodesFactory {
			@Override
			protected Resource loadResource(String path) {
				if (SQLErrorCodesFactory.SQL_ERROR_CODE_OVERRIDE_PATH.equals(path)) {
					return new ClassPathResource("custom-error-codes.xml", SQLErrorCodesFactoryTests.class);
				}
				return null;
			}
		}

		// Should have loaded without error
		TestSQLErrorCodesFactory sf = new TestSQLErrorCodesFactory();
		assertEquals(1, sf.getErrorCodes("Oracle").getCustomTranslations().length);
		CustomSQLErrorCodesTranslation translation =
				sf.getErrorCodes("Oracle").getCustomTranslations()[0];
		assertEquals(CustomErrorCodeException.class, translation.getExceptionClass());
		assertEquals(1, translation.getErrorCodes().length);
	}
