		public Tester(int i) throws Exception {
			counter++;
			if (i == 1) {
				throw new IllegalArgumentException("IllegalArgumentException for 1");
			}
			if (i == 2) {
				throw new RuntimeException("RuntimeException for 2");
			}
			if (i == 4) {
				throw new TestException();
			}
			this.i = i;
		}
