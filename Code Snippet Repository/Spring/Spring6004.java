		@Override
		public int compareTo(Apple that) {
			this.gotComparedTo = that;
			if (this.i < that.i) {
				return -1;
			}
			else if (this.i > that.i) {
				return +1;
			}
			else {
				return 0;
			}
		}
