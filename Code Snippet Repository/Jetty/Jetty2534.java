    @Override
    protected String apply(String target, HttpServletRequest request, HttpServletResponse response, Matcher matcher)
            throws IOException
    {
        target=_location;
        for (int g=1;g<=matcher.groupCount();g++)
        {
            String group = matcher.group(g);
            target=target.replaceAll("\\$"+g,group);
        }
        
        target = response.encodeRedirectURL(target);
        response.setHeader("Location",RedirectUtil.toRedirectURL(request,target));
        response.setStatus(_statusCode);
        response.getOutputStream().flush(); // no output / content
        response.getOutputStream().close();
        return target;
    }
