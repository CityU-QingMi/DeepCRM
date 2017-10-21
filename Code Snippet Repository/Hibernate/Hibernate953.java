		public CollectionIdSourceImpl(
				ColumnSource columnSource,
				HibernateTypeSourceImpl typeSource,
				String generator,
				final Map<String, String> parameters) {
			this.columnSource = columnSource;
			this.typeSource = typeSource;
			this.generator = generator;
			if ( CollectionHelper.isEmpty( parameters ) ) {
				this.parameters = Collections.emptyMap();
			}
			else {
				this.parameters = Collections.unmodifiableMap( parameters );
			}
		}
