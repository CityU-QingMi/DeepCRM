    public void setValue(ELContext context, Object value)
            throws NullPointerException, PropertyNotFoundException,
            PropertyNotWritableException, ELException {
        try {
            this.target.setValue(context, value);
        } catch (PropertyNotWritableException e) {
            if (e instanceof JspPropertyNotWritableException) throw e;
            throw new JspPropertyNotWritableException(this.mark, e);
        } catch (PropertyNotFoundException e) {
            if (e instanceof JspPropertyNotFoundException) throw e;
            throw new JspPropertyNotFoundException(this.mark, e);
        } catch (ELException e) {
            if (e instanceof JspELException) throw e;
            throw new JspELException(this.mark, e);
        }
    }
