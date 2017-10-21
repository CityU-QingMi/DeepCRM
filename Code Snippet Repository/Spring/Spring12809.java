		@Override
		protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
			RequestMapping annot = AnnotationUtils.findAnnotation(method, RequestMapping.class);
			if (annot != null) {
				return new RequestMappingInfo(
					new PatternsRequestCondition(annot.value(), getUrlPathHelper(), getPathMatcher(), true, true),
					new RequestMethodsRequestCondition(annot.method()),
					new ParamsRequestCondition(annot.params()),
					new HeadersRequestCondition(annot.headers()),
					new ConsumesRequestCondition(annot.consumes(), annot.headers()),
					new ProducesRequestCondition(annot.produces(), annot.headers()), null);
			}
			else {
				return null;
			}
		}
