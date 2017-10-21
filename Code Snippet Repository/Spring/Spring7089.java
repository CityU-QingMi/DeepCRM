		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}

			MyBean bean = (MyBean) o;

			if (foo != null ? !foo.equals(bean.foo) : bean.foo != null) {
				return false;
			}

			return true;
		}
