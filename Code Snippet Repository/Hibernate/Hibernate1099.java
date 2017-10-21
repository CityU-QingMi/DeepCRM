	private static String inferGenericTypeName(CtClass ctClass, SignatureAttribute.Type genericSignature) {
		// infer targetEntity from generic type signature
		if ( isAssignable( ctClass, Collection.class.getName() ) ) {
			return ( (SignatureAttribute.ClassType) genericSignature ).getTypeArguments()[0].getType().jvmTypeName();
		}
		if ( isAssignable( ctClass, Map.class.getName() ) ) {
			return ( (SignatureAttribute.ClassType) genericSignature ).getTypeArguments()[1].getType().jvmTypeName();
		}
		return ctClass.getName();
	}
