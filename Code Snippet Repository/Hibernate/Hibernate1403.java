	private <A extends Annotation> AnnotationValueGeneration<A> instantiateAndInitializeValueGeneration(
			A annotation,
			Class<? extends AnnotationValueGeneration<?>> generationType,
			XProperty property) {

		try {
			// This will cause a CCE in case the generation type doesn't match the annotation type; As this would be a
			// programming error of the generation type developer and thus should show up during testing, we don't
			// check this explicitly; If required, this could be done e.g. using ClassMate
			@SuppressWarnings( "unchecked" )
			AnnotationValueGeneration<A> valueGeneration = (AnnotationValueGeneration<A>) generationType.newInstance();
			valueGeneration.initialize(
					annotation,
					buildingContext.getBuildingOptions().getReflectionManager().toClass( property.getType() )
			);

			return valueGeneration;
		}
		catch (HibernateException e) {
			throw e;
		}
		catch (Exception e) {
			throw new AnnotationException(
					"Exception occurred during processing of generator annotation:" + StringHelper.qualify(
							holder.getPath(),
							name
					), e
			);
		}
	}
