		public void twelve(int... vargs) {
			if (vargs == null) {
				i = 0;
			}
			else {
				i = 0;
				for (int varg: vargs) {
					i += varg;
				}
			}
		}
