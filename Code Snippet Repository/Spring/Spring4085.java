		@Override
		public void validate(@Nullable Object obj, Errors errors) {
			TestBean tb = (TestBean) obj;
			if (tb == null || "XXX".equals(tb.getName())) {
				errors.rejectValue("", "SPOUSE_NOT_AVAILABLE");
				return;
			}
			if (tb.getAge() < 32) {
				errors.rejectValue("age", "TOO_YOUNG", "simply too young");
			}
		}
