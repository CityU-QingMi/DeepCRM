	private static boolean isApplicable(Member member, Class<?> sourceClass) {
		if (member instanceof Method) {
			Method method = (Method) member;
			return (!Modifier.isStatic(method.getModifiers()) ?
					ClassUtils.isAssignable(method.getDeclaringClass(), sourceClass) :
					method.getParameterTypes()[0] == sourceClass);
		}
		else if (member instanceof Constructor) {
			Constructor<?> ctor = (Constructor<?>) member;
			return (ctor.getParameterTypes()[0] == sourceClass);
		}
		else {
			return false;
		}
	}
