    public int doAfterBody() throws JspException {
        try
        {
            SimpleDateFormat format = new SimpleDateFormat(body.getString());
            format.setTimeZone(TimeZone.getTimeZone(tz));
            body.getEnclosingWriter().write(format.format(new Date()));
            return SKIP_BODY;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            throw new JspTagException(ex.toString());
        }
    }
