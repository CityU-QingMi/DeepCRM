	@Test
	public void testWildCardNameRecognized() throws Exception {
		class WildcardSQLErrorCodesFactory extends SQLErrorCodesFactory {
			@Override
			protected Resource loadResource(String path) {
				if (SQLErrorCodesFactory.SQL_ERROR_CODE_OVERRIDE_PATH.equals(path)) {
					return new ClassPathResource("wildcard-error-codes.xml", SQLErrorCodesFactoryTests.class);
				}
				return null;
			}
		}

		WildcardSQLErrorCodesFactory factory = new WildcardSQLErrorCodesFactory();
		SQLErrorCodes sec = getErrorCodesFromDataSource("DB2", factory);
		assertIsDB2(sec);
		sec = getErrorCodesFromDataSource("DB2 UDB for Xxxxx", factory);
		assertIsDB2(sec);

		sec = getErrorCodesFromDataSource("DB3", factory);
		assertIsDB2(sec);
		sec = getErrorCodesFromDataSource("DB3/", factory);
		assertIsDB2(sec);
		sec = getErrorCodesFromDataSource("/DB3", factory);
		assertIsDB2(sec);
		sec = getErrorCodesFromDataSource("/DB3", factory);
		assertIsDB2(sec);
		sec = getErrorCodesFromDataSource("/DB3/", factory);
		assertIsDB2(sec);
		sec = getErrorCodesFromDataSource("DB-3", factory);
		assertIsEmpty(sec);

		sec = getErrorCodesFromDataSource("DB1", factory);
		assertIsDB2(sec);
		sec = getErrorCodesFromDataSource("DB1/", factory);
		assertIsDB2(sec);
		sec = getErrorCodesFromDataSource("/DB1", factory);
		assertIsEmpty(sec);
		sec = getErrorCodesFromDataSource("/DB1/", factory);
		assertIsEmpty(sec);

		sec = getErrorCodesFromDataSource("DB0", factory);
		assertIsDB2(sec);
		sec = getErrorCodesFromDataSource("/DB0", factory);
		assertIsDB2(sec);
		sec = getErrorCodesFromDataSource("DB0/", factory);
		assertIsEmpty(sec);
		sec = getErrorCodesFromDataSource("/DB0/", factory);
		assertIsEmpty(sec);
	}
