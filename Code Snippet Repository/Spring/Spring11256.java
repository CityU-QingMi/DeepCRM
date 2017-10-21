		@Override
		public RequestMappingInfo build() {
			RequestedContentTypeResolver contentTypeResolver = this.options.getContentTypeResolver();

			PathPatternParser parser = this.options.getPatternParser() != null ?
					this.options.getPatternParser() : new PathPatternParser();
			PatternsRequestCondition patternsCondition = new PatternsRequestCondition(parse(this.paths, parser));

			return new RequestMappingInfo(this.mappingName, patternsCondition,
					new RequestMethodsRequestCondition(methods),
					new ParamsRequestCondition(this.params),
					new HeadersRequestCondition(this.headers),
					new ConsumesRequestCondition(this.consumes, this.headers),
					new ProducesRequestCondition(this.produces, this.headers, contentTypeResolver),
					this.customCondition);
		}
