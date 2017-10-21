	@Test
	public void testLookupOrder() {
		class TestSQLErrorCodesFactory extends SQLErrorCodesFactory {
			private int lookups = 0;
			@Override
			protected Resource loadResource(String path) {
				++lookups;
				if (lookups == 1) {
					assertEquals(SQLErrorCodesFactory.SQL_ERROR_CODE_DEFAULT_PATH, path);
					return null;
				}
				else {
					// Should have only one more lookup
					assertEquals(2, lookups);
					assertEquals(SQLErrorCodesFactory.SQL_ERROR_CODE_OVERRIDE_PATH, path);
					return null;
				}
			}
		}

		// Should have failed to load without error
		TestSQLErrorCodesFactory sf = new TestSQLErrorCodesFactory();
		assertTrue(sf.getErrorCodes("XX").getBadSqlGrammarCodes().length == 0);
		assertTrue(sf.getErrorCodes("Oracle").getDataIntegrityViolationCodes().length == 0);
	}
