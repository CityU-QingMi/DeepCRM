    private Request redirect(Request request, Response response, Response.CompleteListener listener, URI location, String method)
    {
        HttpRequest httpRequest = (HttpRequest)request;
        HttpConversation conversation = httpRequest.getConversation();
        Integer redirects = (Integer)conversation.getAttribute(ATTRIBUTE);
        if (redirects == null)
            redirects = 0;
        if (redirects < client.getMaxRedirects())
        {
            ++redirects;
            conversation.setAttribute(ATTRIBUTE, redirects);
            return sendRedirect(httpRequest, response, listener, location, method);
        }
        else
        {
            fail(request, response, new HttpResponseException("Max redirects exceeded " + redirects, response));
            return null;
        }
    }
