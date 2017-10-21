	public JspWriter pushBody(Writer writer) {
		depth++;
		if (depth >= outs.length) {
			BodyContentImpl[] newOuts = new BodyContentImpl[depth + 1];
			for (int i = 0; i < outs.length; i++) {
				newOuts[i] = outs[i];
			}
			newOuts[depth] = new BodyContentImpl(out);
			outs = newOuts;
		}

		outs[depth].setWriter(writer);
		out = outs[depth];

		// Update the value of the "out" attribute in the page scope
		// attribute namespace of this PageContext
		setAttribute(OUT, out);

		return outs[depth];
	}
