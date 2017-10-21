    protected HtmlClosure getGxpClosure() {
        final Gxp gxp = getUseInstances() ? GxpInstance.getInstance(getGxpName()) : Gxp.getInstance(getGxpName());

        if (null == gxp) {
            // TODO(lwerner): OGNL or Struts 2 seems to be swallowing this exception
            // rather than logging or rethrowing it, so you never see this message
            // TODO(dpb): Is this true now that this work is not done in a setter?
            throw new NullPointerException("The GXP " + getGxpName()
                    + " could not be loaded.  This is probably because you have"
                    + " a typo in your config.");
        }
        container.inject(gxp);
        return new HtmlClosure() {
            public void write(Appendable out, GxpContext gxpContext) throws IOException {
                gxp.write(out, gxpContext);
            }
        };
    }
