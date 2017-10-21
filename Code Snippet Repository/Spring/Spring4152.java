    private Attribute readAttribute(final Attribute[] attrs, final String type,
            final int off, final int len, final char[] buf, final int codeOff,
            final Label[] labels) {
        for (int i = 0; i < attrs.length; ++i) {
            if (attrs[i].type.equals(type)) {
                return attrs[i].read(this, off, len, buf, codeOff, labels);
            }
        }
        return new Attribute(type).read(this, off, len, null, -1, null);
    }
