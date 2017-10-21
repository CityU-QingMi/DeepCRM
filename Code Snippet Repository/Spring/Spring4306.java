	public Annotation[] getParameterAnnotations() {
		Annotation[] paramAnns = this.parameterAnnotations;
		if (paramAnns == null) {
			Annotation[][] annotationArray = this.executable.getParameterAnnotations();
			if (this.parameterIndex >= 0 && this.parameterIndex < annotationArray.length) {
				paramAnns = adaptAnnotationArray(annotationArray[this.parameterIndex]);
			}
			else {
				paramAnns = new Annotation[0];
			}
			this.parameterAnnotations = paramAnns;
		}
		return paramAnns;
	}
