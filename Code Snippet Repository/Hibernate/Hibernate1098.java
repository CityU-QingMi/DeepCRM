	private static String inferMethodTypeName(CtMethod method) {
		try {
			if ( method.getMethodInfo2().getAttribute( SignatureAttribute.tag ) == null ) {
				return method.getReturnType().getName();
			}
			return inferGenericTypeName(
					method.getReturnType(),
					SignatureAttribute.toMethodSignature( method.getGenericSignature() ).getReturnType()
			);
		}
		catch (BadBytecode ignore) {
			return null;
		}
		catch (NotFoundException e) {
			return null;
		}
	}
