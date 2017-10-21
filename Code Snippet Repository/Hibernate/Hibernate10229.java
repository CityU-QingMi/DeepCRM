		@Override
		protected void sortMethods(List<FrameworkMethod> computedTestMethods) {
			super.sortMethods( computedTestMethods );
			Collections.sort(
					computedTestMethods, new Comparator<FrameworkMethod>() {
				private int getPriority(FrameworkMethod fm) {
					Priority p = fm.getAnnotation( Priority.class );
					return p == null ? 0 : p.value();
				}

				@Override
				public int compare(FrameworkMethod fm1, FrameworkMethod fm2) {
					return getPriority( fm2 ) - getPriority( fm1 );
				}
			}
			);
		}
