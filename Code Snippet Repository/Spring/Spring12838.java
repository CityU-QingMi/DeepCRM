		@Override
		protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
			RequestMapping annotation = AnnotatedElementUtils.findMergedAnnotation(method, RequestMapping.class);
			if (annotation != null) {
				return new RequestMappingInfo(
						new PatternsRequestCondition(annotation.value(), getUrlPathHelper(), getPathMatcher(), true, true),
						new RequestMethodsRequestCondition(annotation.method()),
						new ParamsRequestCondition(annotation.params()),
						new HeadersRequestCondition(annotation.headers()),
						new ConsumesRequestCondition(annotation.consumes(), annotation.headers()),
						new ProducesRequestCondition(annotation.produces(), annotation.headers()), null);
			}
			else {
				return null;
			}
		}
