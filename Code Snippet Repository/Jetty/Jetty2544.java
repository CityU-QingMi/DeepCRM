    @Override
    public String matchAndApply(String target, HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        // best to decide the request uri and validate that
        // String uri = request.getRequestURI();
        String uri = URIUtil.decodePath(request.getRequestURI());

        for (int i = 0; i < uri.length();)
        {
            int codepoint = uri.codePointAt(i);

            if (!isValidChar(uri.codePointAt(i)))
            {

                int code = Integer.parseInt(_code);

                // status code 400 and up are error codes so include a reason
                if (code >= 400)
                {
                    response.sendError(code,_reason);
                }
                else
                {
                    response.setStatus(code);
                }

                // we have matched, return target and consider it is handled
                return target;
            }
            i += Character.charCount(codepoint);
        }

        // we have not matched so return null
        return null;
    }
