		@Override
		public boolean equals(Object other) {
			if (this == other) {
				return true;
			}
			if (!super.equals(other)) {
				return false;
			}
			ClassFilter otherCf1 = ClassFilter.TRUE;
			ClassFilter otherCf2 = ClassFilter.TRUE;
			if (other instanceof ClassFilterAwareUnionMethodMatcher) {
				ClassFilterAwareUnionMethodMatcher cfa = (ClassFilterAwareUnionMethodMatcher) other;
				otherCf1 = cfa.cf1;
				otherCf2 = cfa.cf2;
			}
			return (this.cf1.equals(otherCf1) && this.cf2.equals(otherCf2));
		}
