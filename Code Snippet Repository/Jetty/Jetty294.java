    public Request redirect(Request request, Response response, Response.CompleteListener listener)
    {
        if (isRedirect(response))
        {
            String location = response.getHeaders().get("Location");
            URI newURI = extractRedirectURI(response);
            if (newURI != null)
            {
                if (LOG.isDebugEnabled())
                    LOG.debug("Redirecting to {} (Location: {})", newURI, location);
                return redirect(request, response, listener, newURI);
            }
            else
            {
                fail(request, response, new HttpResponseException("Invalid 'Location' header: " + location, response));
                return null;
            }
        }
        else
        {
            fail(request, response, new HttpResponseException("Cannot redirect: " + response, response));
            return null;
        }
    }
