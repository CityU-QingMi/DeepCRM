	public QueryParameters createCopyUsing(RowSelection selection) {
		QueryParameters copy = new QueryParameters(
				this.positionalParameterTypes,
				this.positionalParameterValues,
				this.namedParameters,
				this.lockOptions,
				selection,
				this.isReadOnlyInitialized,
				this.readOnly,
				this.cacheable,
				this.cacheRegion,
				this.comment,
				this.queryHints,
				this.collectionKeys,
				this.optionalObject,
				this.optionalEntityName,
				this.optionalId,
				this.resultTransformer
		);
		copy.processedSQL = this.processedSQL;
		copy.processedPositionalParameterTypes = this.processedPositionalParameterTypes;
		copy.processedPositionalParameterValues = this.processedPositionalParameterValues;
		copy.passDistinctThrough = this.passDistinctThrough;
		return copy;
	}
