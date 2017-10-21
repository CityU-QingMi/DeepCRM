	@Test
	public void testConcatenation() {
		if ( getDialect() instanceof MySQLDialect || getDialect() instanceof SybaseDialect
				|| getDialect() instanceof Sybase11Dialect
				|| getDialect() instanceof SybaseASE15Dialect
				|| getDialect() instanceof SybaseAnywhereDialect
				|| getDialect() instanceof SQLServerDialect 
				|| getDialect() instanceof IngresDialect) {
			// SybaseASE15Dialect and SybaseAnywhereDialect support '||'
			// MySQL uses concat(x, y, z)
			// SQL Server replaces '||' with '+'
			//
			// this is syntax checked in {@link ASTParserLoadingTest#testConcatenation} 
			// Ingres supports both "||" and '+' but IngresDialect originally
			// uses '+' operator; updated Ingres9Dialect to use "||".
			return;
		}
		assertTranslation("from Human h where h.nickName = '1' || 'ov' || 'tha' || 'few'");
	}
