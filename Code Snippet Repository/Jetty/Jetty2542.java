    @Override
    public String apply(String target, HttpServletRequest request, HttpServletResponse response, Matcher matcher) throws IOException
    {
        target=_replacement;
        String query=_query;
        for (int g=1;g<=matcher.groupCount();g++)
        {
            String group=matcher.group(g);
            if (group==null)
                group="";
            else
                group = Matcher.quoteReplacement(group);
            target=target.replaceAll("\\$"+g,group);
            if (query!=null)
                query=query.replaceAll("\\$"+g,group);
        }

        if (query!=null)
        {
            if (_queryGroup)
                query=query.replace("$Q",request.getQueryString()==null?"":request.getQueryString());
            request.setAttribute("org.eclipse.jetty.rewrite.handler.RewriteRegexRule.Q",query);
        }
        
        return target;
    }
