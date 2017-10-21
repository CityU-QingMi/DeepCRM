	@Test
	public void testInvalidUserDefinedCodeFormat() {
		class TestSQLErrorCodesFactory extends SQLErrorCodesFactory {
			@Override
			protected Resource loadResource(String path) {
				if (SQLErrorCodesFactory.SQL_ERROR_CODE_OVERRIDE_PATH.equals(path)) {
					// Guaranteed to be on the classpath, but most certainly NOT XML
					return new ClassPathResource("SQLExceptionTranslator.class", SQLErrorCodesFactoryTests.class);
				}
				return null;
			}
		}

		// Should have failed to load without error
		TestSQLErrorCodesFactory sf = new TestSQLErrorCodesFactory();
		assertTrue(sf.getErrorCodes("XX").getBadSqlGrammarCodes().length == 0);
		assertEquals(0, sf.getErrorCodes("Oracle").getBadSqlGrammarCodes().length);
	}
