	private static String inferFieldTypeName(CtField field) {
		try {
			if ( field.getFieldInfo2().getAttribute( SignatureAttribute.tag ) == null ) {
				return field.getType().getName();
			}
			return inferGenericTypeName(
					field.getType(),
					SignatureAttribute.toTypeSignature( field.getGenericSignature() )
			);
		}
		catch (BadBytecode ignore) {
			return null;
		}
		catch (NotFoundException e) {
			return null;
		}
	}
