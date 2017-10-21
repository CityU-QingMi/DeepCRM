	@Test
	public void remove() {
		this.simpAttributes.setAttribute("name", "value");

		Object removed = this.scope.remove("name");
		assertThat(removed, is("value"));
		assertThat(this.simpAttributes.getAttribute("name"), nullValue());

		removed = this.scope.remove("name");
		assertThat(removed, nullValue());
	}
