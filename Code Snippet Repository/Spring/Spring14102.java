	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof TestPrincipal)) {
			return false;
		}
		TestPrincipal p = (TestPrincipal) obj;
		return this.name.equals(p.name);
	}
