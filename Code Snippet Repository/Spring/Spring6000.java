		public void fifteen(String a, int[]... vargs) {
			if (vargs == null) {
				s = a+"::";
			}
			else {
				s = a+"::";
				for (int[] varg: vargs) {
					s += "{";
					for (int v: varg) {
						s += Integer.toString(v);
					}
					s += "}";
				}
			}
		}
