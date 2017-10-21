	private void assertDefaults(MergedSqlConfig cfg) {
		assertNotNull(cfg);
		assertEquals("dataSource", "", cfg.getDataSource());
		assertEquals("transactionManager", "", cfg.getTransactionManager());
		assertEquals("transactionMode", INFERRED, cfg.getTransactionMode());
		assertEquals("encoding", "", cfg.getEncoding());
		assertEquals("separator", DEFAULT_STATEMENT_SEPARATOR, cfg.getSeparator());
		assertEquals("commentPrefix", DEFAULT_COMMENT_PREFIX, cfg.getCommentPrefix());
		assertEquals("blockCommentStartDelimiter", DEFAULT_BLOCK_COMMENT_START_DELIMITER,
			cfg.getBlockCommentStartDelimiter());
		assertEquals("blockCommentEndDelimiter", DEFAULT_BLOCK_COMMENT_END_DELIMITER, cfg.getBlockCommentEndDelimiter());
		assertEquals("errorMode", FAIL_ON_ERROR, cfg.getErrorMode());
	}
