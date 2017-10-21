		public void concat(String... vargs) {
			if (vargs == null) {
				s = "";
			}
			else {
				s = "";
				for (String varg : vargs) {
					s += varg;
				}
			}
		}
