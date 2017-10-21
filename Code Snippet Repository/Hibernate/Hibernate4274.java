	@Override
	public Object get(Object owner) {
		try {
			return field.get( owner );

//			// We don't want to trigger lazy loading of byte code enhanced attributes
//			if ( isAttributeLoaded( owner ) ) {
//				return getterMethod.invoke( owner );
//			}
//			return null;
		}
//		catch (InvocationTargetException ite) {
//			throw new PropertyAccessException(
//					ite,
//					"Exception occurred inside",
//					false,
//					containerClass,
//					propertyName
//			);
//		}
		catch (IllegalAccessException iae) {
			throw new PropertyAccessException(
					iae,
					"IllegalAccessException occurred while calling",
					false,
					containerClass,
					propertyName
			);
			//cannot occur
		}
		catch (IllegalArgumentException iae) {
			LOG.illegalPropertyGetterArgument( containerClass.getName(), propertyName );
			throw new PropertyAccessException(
					iae,
					"IllegalArgumentException occurred calling",
					false,
					containerClass,
					propertyName
			);
		}
	}
