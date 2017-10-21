	@Test
	public void localConfigWithCustomValues() throws Exception {
		Method method = getClass().getMethod("localConfigMethodWithCustomValues");
		SqlConfig localSqlConfig = method.getAnnotation(Sql.class).config();
		MergedSqlConfig cfg = new MergedSqlConfig(localSqlConfig, getClass());
		assertNotNull(cfg);
		assertEquals("dataSource", "ds", cfg.getDataSource());
		assertEquals("transactionManager", "txMgr", cfg.getTransactionManager());
		assertEquals("transactionMode", ISOLATED, cfg.getTransactionMode());
		assertEquals("encoding", "enigma", cfg.getEncoding());
		assertEquals("separator", "\n", cfg.getSeparator());
		assertEquals("commentPrefix", "`", cfg.getCommentPrefix());
		assertEquals("blockCommentStartDelimiter", "<<", cfg.getBlockCommentStartDelimiter());
		assertEquals("blockCommentEndDelimiter", ">>", cfg.getBlockCommentEndDelimiter());
		assertEquals("errorMode", IGNORE_FAILED_DROPS, cfg.getErrorMode());
	}
