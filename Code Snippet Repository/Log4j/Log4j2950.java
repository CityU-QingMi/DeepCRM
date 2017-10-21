    @Override
    public int doEndTag() throws JspException {
        try {
            final Enumeration<String> names = this.pageContext.getAttributeNamesInScope(this.scope);
            this.pageContext.getOut().write("<dl>");
            while (names != null && names.hasMoreElements()) {
                final String name = names.nextElement();
                final Object value = this.pageContext.getAttribute(name, this.scope);

                this.pageContext.getOut().write("<dt><code>" + name + "</code></dt>");
                this.pageContext.getOut().write("<dd><code>" + value + "</code></dd>");
            }
            this.pageContext.getOut().write("</dl>");
        } catch (final IOException e) {
            throw new JspException("Could not write scope contents. Cause:  " + e.toString(), e);
        }

        return Tag.EVAL_PAGE;
    }
