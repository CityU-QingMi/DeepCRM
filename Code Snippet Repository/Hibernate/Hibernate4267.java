	public PropertyAccessMixedImpl(
			PropertyAccessStrategy strategy,
			Class containerJavaType,
			String propertyName) {
		this.strategy = strategy;

		AccessType propertyAccessType = getAccessType( containerJavaType, propertyName );

		switch ( propertyAccessType ) {
			case FIELD: {
				Field field = fieldOrNull( containerJavaType, propertyName );
				if ( field == null ) {
					throw new PropertyAccessBuildingException(
						"Could not locate field for property named [" + containerJavaType.getName() + "#" + propertyName + "]"
					);
				}
				this.getter = fieldGetter( containerJavaType, propertyName, field );
				this.setter = fieldSetter( containerJavaType, propertyName, field );
				break;
			}
			case PROPERTY: {
				Method getterMethod = getterMethodOrNull( containerJavaType, propertyName );
				if ( getterMethod == null ) {
					throw new PropertyAccessBuildingException(
						"Could not locate getter for property named [" + containerJavaType.getName() + "#" + propertyName + "]"
					);
				}
				Method setterMethod = setterMethodOrNull( containerJavaType, propertyName, getterMethod.getReturnType() );

				this.getter = propertyGetter( containerJavaType, propertyName, getterMethod );
				this.setter = propertySetter( containerJavaType, propertyName, setterMethod );
				break;
			}
			default: {
				throw new PropertyAccessBuildingException(
					"Invalid access type " + propertyAccessType + " for property named [" + containerJavaType.getName() + "#" + propertyName + "]"
				);
			}
		}
	}
