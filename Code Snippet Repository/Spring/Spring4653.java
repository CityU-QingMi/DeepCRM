	@Override
	public void visitEnd() {
		super.visitEnd();

		Class<?> annotationClass = this.attributes.annotationType();
		if (annotationClass != null) {
			List<AnnotationAttributes> attributeList = this.attributesMap.get(this.annotationType);
			if (attributeList == null) {
				this.attributesMap.add(this.annotationType, this.attributes);
			}
			else {
				attributeList.add(0, this.attributes);
			}
			Set<Annotation> visited = new LinkedHashSet<>();
			Annotation[] metaAnnotations = AnnotationUtils.getAnnotations(annotationClass);
			if (!ObjectUtils.isEmpty(metaAnnotations)) {
				for (Annotation metaAnnotation : metaAnnotations) {
					if (!AnnotationUtils.isInJavaLangAnnotationPackage(metaAnnotation)) {
						recursivelyCollectMetaAnnotations(visited, metaAnnotation);
					}
				}
			}
			Set<String> metaAnnotationTypeNames = new LinkedHashSet<>(visited.size());
			for (Annotation ann : visited) {
				metaAnnotationTypeNames.add(ann.annotationType().getName());
			}
			this.metaAnnotationMap.put(annotationClass.getName(), metaAnnotationTypeNames);
		}
	}
