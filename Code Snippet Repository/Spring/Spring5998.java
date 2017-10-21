		public void thirteen(String a, String... vargs) {
			if (vargs == null) {
				s = a + "::";
			}
			else {
				s = a+"::";
				for (String varg: vargs) {
					s += varg;
				}
			}
		}
