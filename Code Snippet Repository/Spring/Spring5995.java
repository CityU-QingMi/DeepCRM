		public void concat2(Object... vargs) {
			if (vargs == null) {
				s = "";
			}
			else {
				s = "";
				for (Object varg : vargs) {
					s += varg;
				}
			}
		}
