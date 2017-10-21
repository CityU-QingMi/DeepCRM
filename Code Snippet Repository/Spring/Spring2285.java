		@Override
		protected boolean isEligibleForOverriding(String className) {
			if (isExcluded(className) || ContextTypeMatchClassLoader.this.isExcluded(className)) {
				return false;
			}
			ReflectionUtils.makeAccessible(findLoadedClassMethod);
			ClassLoader parent = getParent();
			while (parent != null) {
				if (ReflectionUtils.invokeMethod(findLoadedClassMethod, parent, className) != null) {
					return false;
				}
				parent = parent.getParent();
			}
			return true;
		}
