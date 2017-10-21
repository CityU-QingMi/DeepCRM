	public HqlSqlWalker(
			QueryTranslatorImpl qti,
			SessionFactoryImplementor sfi,
			HqlParser parser,
			Map tokenReplacements,
			String collectionRole) {
		setASTFactory( new SqlASTFactory( this ) );
		// Initialize the error handling delegate.
		this.parseErrorHandler = new ErrorCounter( qti.getQueryString() );
		this.queryTranslatorImpl = qti;
		this.sessionFactoryHelper = new SessionFactoryHelper( sfi );
		this.literalProcessor = new LiteralProcessor( this );
		this.tokenReplacements = tokenReplacements;
		this.collectionFilterRole = collectionRole;
		this.hqlParser = parser;
		this.printer = new ASTPrinter( SqlTokenTypes.class );
	}
