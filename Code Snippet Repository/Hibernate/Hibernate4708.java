	public static OrderByTranslation translate(TranslationContext context, String fragment) {
		GeneratedOrderByLexer lexer = new GeneratedOrderByLexer( new StringReader( fragment ) );

		// Perform the parsing (and some analysis/resolution).  Another important aspect is the collection
		// of "column references" which are important later to seek out replacement points in the
		// translated fragment.
		OrderByFragmentParser parser = new OrderByFragmentParser( lexer, context );
		try {
			parser.orderByFragment();
		}
		catch ( HibernateException e ) {
			throw e;
		}
		catch ( Throwable t ) {
			throw new HibernateException( "Unable to parse order-by fragment", t );
		}

		if ( LOG.isTraceEnabled() ) {
			ASTPrinter printer = new ASTPrinter( OrderByTemplateTokenTypes.class );
			LOG.trace( printer.showAsString( parser.getAST(), "--- {order-by fragment} ---" ) );
		}

		// Render the parsed tree to text.
		OrderByFragmentRenderer renderer = new OrderByFragmentRenderer( context.getSessionFactory() );
		try {
			renderer.orderByFragment( parser.getAST() );
		}
		catch ( HibernateException e ) {
			throw e;
		}
		catch ( Throwable t ) {
			throw new HibernateException( "Unable to render parsed order-by fragment", t );
		}

		return new StandardOrderByTranslationImpl( renderer.getRenderedFragment(), parser.getColumnReferences() );
	}
