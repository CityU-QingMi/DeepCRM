	@Test
	public void globalConfig() throws Exception {
		Method method = GlobalConfigClass.class.getMethod("globalConfigMethod");
		SqlConfig localSqlConfig = method.getAnnotation(Sql.class).config();
		MergedSqlConfig cfg = new MergedSqlConfig(localSqlConfig, GlobalConfigClass.class);
		assertNotNull(cfg);
		assertEquals("dataSource", "", cfg.getDataSource());
		assertEquals("transactionManager", "", cfg.getTransactionManager());
		assertEquals("transactionMode", INFERRED, cfg.getTransactionMode());
		assertEquals("encoding", "global", cfg.getEncoding());
		assertEquals("separator", "\n", cfg.getSeparator());
		assertEquals("commentPrefix", DEFAULT_COMMENT_PREFIX, cfg.getCommentPrefix());
		assertEquals("blockCommentStartDelimiter", DEFAULT_BLOCK_COMMENT_START_DELIMITER,
			cfg.getBlockCommentStartDelimiter());
		assertEquals("blockCommentEndDelimiter", DEFAULT_BLOCK_COMMENT_END_DELIMITER, cfg.getBlockCommentEndDelimiter());
		assertEquals("errorMode", IGNORE_FAILED_DROPS, cfg.getErrorMode());
	}
