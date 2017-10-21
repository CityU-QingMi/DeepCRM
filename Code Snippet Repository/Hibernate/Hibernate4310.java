	@Override
	public CriteriaInterpretation interpret(RenderingContext renderingContext) {
		final String jpaqlString = renderQuery( renderingContext );
		return new CriteriaInterpretation() {
			@Override
			@SuppressWarnings("unchecked")
			public QueryImplementor buildCompiledQuery(
					SessionImplementor entityManager,
					final InterpretedParameterMetadata interpretedParameterMetadata) {

				final Map<String,Class> implicitParameterTypes = extractTypeMap( interpretedParameterMetadata.implicitParameterBindings() );

				QueryImplementor query = entityManager.createQuery(
						jpaqlString,
						null,
						null,
						new HibernateEntityManagerImplementor.QueryOptions() {
							@Override
							public List<ValueHandlerFactory.ValueHandler> getValueHandlers() {
								return null;
							}

							@Override
							public Map<String, Class> getNamedParameterExplicitTypes() {
								return implicitParameterTypes;
							}

							@Override
							public ResultMetadataValidator getResultMetadataValidator() {
								return null;
							}
						}
				);

				for ( ImplicitParameterBinding implicitParameterBinding : interpretedParameterMetadata.implicitParameterBindings() ) {
					implicitParameterBinding.bind( query );
				}

				return query;
			}

			private Map<String, Class> extractTypeMap(List<ImplicitParameterBinding> implicitParameterBindings) {
				final HashMap<String,Class> map = new HashMap<>();
				for ( ImplicitParameterBinding implicitParameter : implicitParameterBindings ) {
					map.put( implicitParameter.getParameterName(), implicitParameter.getJavaType() );
				}
				return map;
			}
		};
	}
