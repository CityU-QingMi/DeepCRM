		@Override
		public <T extends Annotation> boolean hasMethodAnnotation(Class<T> annotationType) {

			// Ensure @ResponseBody-style handling for values collected from a reactive type
			// even if actual return type is ResponseEntity<Flux<T>>

			return ResponseBody.class.equals(annotationType) &&
					this.returnValue instanceof ReactiveTypeHandler.CollectedValuesList ||
					super.hasMethodAnnotation(annotationType);
		}
