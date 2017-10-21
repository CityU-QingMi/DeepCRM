    public boolean isReadOnly(ELContext context) throws NullPointerException,
            PropertyNotFoundException, ELException {
        try {
            return this.target.isReadOnly(context);
        } catch (PropertyNotFoundException e) {
            if (e instanceof JspPropertyNotFoundException) throw e;
            throw new JspPropertyNotFoundException(this.mark, e);
        } catch (ELException e) {
            if (e instanceof JspELException) throw e;
            throw new JspELException(this.mark, e);
        }
    }
