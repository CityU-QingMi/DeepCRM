	@Test
	public void testFindUserDefinedCodes() {
		class TestSQLErrorCodesFactory extends SQLErrorCodesFactory {
			@Override
			protected Resource loadResource(String path) {
				if (SQLErrorCodesFactory.SQL_ERROR_CODE_OVERRIDE_PATH.equals(path)) {
					return new ClassPathResource("test-error-codes.xml", SQLErrorCodesFactoryTests.class);
				}
				return null;
			}
		}

		// Should have loaded without error
		TestSQLErrorCodesFactory sf = new TestSQLErrorCodesFactory();
		assertTrue(sf.getErrorCodes("XX").getBadSqlGrammarCodes().length == 0);
		assertEquals(2, sf.getErrorCodes("Oracle").getBadSqlGrammarCodes().length);
		assertEquals("1", sf.getErrorCodes("Oracle").getBadSqlGrammarCodes()[0]);
		assertEquals("2", sf.getErrorCodes("Oracle").getBadSqlGrammarCodes()[1]);
	}
