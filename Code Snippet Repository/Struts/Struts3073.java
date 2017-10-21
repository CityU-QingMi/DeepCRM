	public JspWriter popBody() {
		depth--;
		if (depth >= 0) {
			out = outs[depth];
		} else {
			out = baseOut;
		}

		// Update the value of the "out" attribute in the page scope
		// attribute namespace of this PageContext
		setAttribute(OUT, out);

		return out;
	}
