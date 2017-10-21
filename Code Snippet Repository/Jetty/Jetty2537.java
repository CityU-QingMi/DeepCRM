    @Override
    public String apply(String target, HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        int code = Integer.parseInt(_code);

        // status code 400 and up are error codes
        if (code >= 400)
        {
            response.sendError(code, _reason);
        }
        else
        {
            response.setStatus(code);
        }
        return target;
    }
