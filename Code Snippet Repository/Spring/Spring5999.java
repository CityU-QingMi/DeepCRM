		public void fourteen(String a, String[]... vargs) {
			if (vargs == null) {
				s = a+"::";
			}
			else {
				s = a+"::";
				for (String[] varg: vargs) {
					s += "{";
					for (String v: varg) {
						s += v;
					}
					s += "}";
				}
			}
		}
