	@Override
	public boolean matches(Method method, @Nullable Class<?> targetClass, boolean beanHasIntroductions) {
		obtainPointcutExpression();
		Method targetMethod = AopUtils.getMostSpecificMethod(method, targetClass);
		ShadowMatch shadowMatch = getShadowMatch(targetMethod, method);

		// Special handling for this, target, @this, @target, @annotation
		// in Spring - we can optimize since we know we have exactly this class,
		// and there will never be matching subclass at runtime.
		if (shadowMatch.alwaysMatches()) {
			return true;
		}
		else if (shadowMatch.neverMatches()) {
			return false;
		}
		else {
			// the maybe case
			if (beanHasIntroductions) {
				return true;
			}
			// A match test returned maybe - if there are any subtype sensitive variables
			// involved in the test (this, target, at_this, at_target, at_annotation) then
			// we say this is not a match as in Spring there will never be a different
			// runtime subtype.
			RuntimeTestWalker walker = getRuntimeTestWalker(shadowMatch);
			return (!walker.testsSubtypeSensitiveVars() ||
					(targetClass != null && walker.testTargetInstanceOfResidue(targetClass)));
		}
	}
