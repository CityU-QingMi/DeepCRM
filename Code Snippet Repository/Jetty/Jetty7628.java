    public void doTag() throws JspException, IOException {
        String formatted =
            new SimpleDateFormat("long".equals(format)?"EEE 'the' d:MMM:yyyy":"d:MM:yy")
            .format(new Date());
        StringTokenizer tok = new StringTokenizer(formatted,":");
        JspContext context = getJspContext();
        context.setAttribute("day", tok.nextToken() );
        context.setAttribute("month", tok.nextToken() );
        context.setAttribute("year", tok.nextToken() );

        JspFragment fragment = getJspBody();
        fragment.invoke(null);
    }
