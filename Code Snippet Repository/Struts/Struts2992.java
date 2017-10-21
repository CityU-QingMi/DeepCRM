    public MethodInfo getMethodInfo(ELContext context)
            throws NullPointerException, PropertyNotFoundException,
            MethodNotFoundException, ELException {
        try {
            return this.target.getMethodInfo(context);
        } catch (MethodNotFoundException e) {
            if (e instanceof JspMethodNotFoundException) throw e;
            throw new JspMethodNotFoundException(this.mark, e);
        } catch (PropertyNotFoundException e) {
            if (e instanceof JspPropertyNotFoundException) throw e;
            throw new JspPropertyNotFoundException(this.mark, e);
        } catch (ELException e) {
            if (e instanceof JspELException) throw e;
            throw new JspELException(this.mark, e);
        }
    }
