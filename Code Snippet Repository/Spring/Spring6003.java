		public Obj3(String s, Float f, int... ints) {
			StringBuilder b = new StringBuilder();
			b.append(s);
			b.append(":");
			b.append(Float.toString(f));
			b.append(":");
			for (int param: ints) {
				b.append(Integer.toString(param));
			}
			output = b.toString();
		}
