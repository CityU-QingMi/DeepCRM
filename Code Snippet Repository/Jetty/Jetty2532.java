    @Override
    public String apply(String target, HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String location = response.encodeRedirectURL(_location);
        response.setHeader("Location",RedirectUtil.toRedirectURL(request,location));
        response.setStatus(_statusCode);
        response.getOutputStream().flush(); // no output / content
        response.getOutputStream().close();
        return target;
    }
