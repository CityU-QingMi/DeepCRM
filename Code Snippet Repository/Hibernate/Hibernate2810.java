	private void compile() throws QueryException, MappingException {
		LOG.trace( "Compiling query" );
		try {
			ParserHelper.parse(
					new PreprocessingParser( tokenReplacements ),
					queryString,
					ParserHelper.HQL_SEPARATORS,
					this
			);
			renderSQL();
		}
		catch (QueryException qe) {
			if ( qe.getQueryString() == null ) {
				throw qe.wrapWithQueryString( queryString );
			}
			else {
				throw qe;
			}
		}
		catch (MappingException me) {
			throw me;
		}
		catch (Exception e) {
			LOG.debug( "Unexpected query compilation problem", e );
			e.printStackTrace();
			throw new QueryException( "Incorrect query syntax", queryString, e );
		}

		postInstantiate();

		compiled = true;

	}
