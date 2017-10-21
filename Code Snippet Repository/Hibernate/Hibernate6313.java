		public DelimitedStringsJavaTypeDescriptor() {
			super(
					Set.class,
					new MutableMutabilityPlan<Set>() {
						@Override
						protected Set deepCopyNotNull(Set value) {
							Set<String> copy = new HashSet<String>();
							copy.addAll( value );
							return copy;
						}
					}
			);
		}
