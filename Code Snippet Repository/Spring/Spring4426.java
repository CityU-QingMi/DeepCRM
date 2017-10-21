		@SuppressWarnings("")
		private AliasDescriptor(Method sourceAttribute, AliasFor aliasFor) {
			Class<?> declaringClass = sourceAttribute.getDeclaringClass();
			Assert.isTrue(declaringClass.isAnnotation(), "sourceAttribute must be from an annotation");

			this.sourceAttribute = sourceAttribute;
			this.sourceAnnotationType = (Class<? extends Annotation>) declaringClass;
			this.sourceAttributeName = sourceAttribute.getName();

			this.aliasedAnnotationType = (Annotation.class == aliasFor.annotation() ?
					this.sourceAnnotationType : aliasFor.annotation());
			this.aliasedAttributeName = getAliasedAttributeName(aliasFor, sourceAttribute);
			if (this.aliasedAnnotationType == this.sourceAnnotationType &&
					this.aliasedAttributeName.equals(this.sourceAttributeName)) {
				String msg = String.format("@AliasFor declaration on attribute '%s' in annotation [%s] points to " +
						"itself. Specify 'annotation' to point to a same-named attribute on a meta-annotation.",
						sourceAttribute.getName(), declaringClass.getName());
				throw new AnnotationConfigurationException(msg);
			}
			try {
				this.aliasedAttribute = this.aliasedAnnotationType.getDeclaredMethod(this.aliasedAttributeName);
			}
			catch (NoSuchMethodException ex) {
				String msg = String.format(
						"Attribute '%s' in annotation [%s] is declared as an @AliasFor nonexistent attribute '%s' in annotation [%s].",
						this.sourceAttributeName, this.sourceAnnotationType.getName(), this.aliasedAttributeName,
						this.aliasedAnnotationType.getName());
				throw new AnnotationConfigurationException(msg, ex);
			}

			this.isAliasPair = (this.sourceAnnotationType == this.aliasedAnnotationType);
		}
