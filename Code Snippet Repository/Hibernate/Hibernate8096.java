	@Test
	public void testExpressionWithParamInFunction() {
		assertTranslation("from Animal a where abs(a.bodyWeight-:param) < 2.0");
		assertTranslation("from Animal a where abs(:param - a.bodyWeight) < 2.0");
		assertTranslation("from Animal where abs(:x - :y) < 2.0");
		assertTranslation("from Animal where lower(upper(:foo)) like 'f%'");
		if ( ! ( getDialect() instanceof SybaseDialect ) &&  ! ( getDialect() instanceof Sybase11Dialect ) &&  ! ( getDialect() instanceof SybaseASE15Dialect ) && ! ( getDialect() instanceof SQLServerDialect ) && ! ( getDialect() instanceof TeradataDialect ) ) {
			// Transact-SQL dialects (except SybaseAnywhereDialect) map the length function -> len; 
			// classic translator does not consider that *when nested*;
			// SybaseAnywhereDialect supports the length function

			assertTranslation("from Animal a where abs(abs(a.bodyWeight - 1.0 + :param) * abs(length('ffobar')-3)) = 3.0");
		}
		if ( !( getDialect() instanceof MySQLDialect ) && ! ( getDialect() instanceof SybaseDialect ) && ! ( getDialect() instanceof Sybase11Dialect ) && !( getDialect() instanceof SybaseASE15Dialect ) && ! ( getDialect() instanceof SybaseAnywhereDialect ) && ! ( getDialect() instanceof SQLServerDialect ) && ! ( getDialect() instanceof TeradataDialect ) ) {
			assertTranslation("from Animal where lower(upper('foo') || upper(:bar)) like 'f%'");
		}
		if ( getDialect() instanceof PostgreSQLDialect || getDialect() instanceof PostgreSQL81Dialect || getDialect() instanceof TeradataDialect) {
			return;
		}
		if ( getDialect() instanceof AbstractHANADialect ) {
			// HANA returns
			// ...al0_7_.mammal where [abs(cast(1 as float(19))-cast(? as float(19)))=1.0]
			return;
		}
		if ( getDialect() instanceof MySQLDialect ) {
			// MySQL dialects are smarter now wrt cast targets.  For example, float (as a db type) is not
			// valid as a cast target for MySQL.  The new parser uses the dialect handling for casts, the old
			// parser does not; so the outputs do not match here...
			return;
		}
		assertTranslation("from Animal where abs(cast(1 as float) - cast(:param as float)) = 1.0");
	}
