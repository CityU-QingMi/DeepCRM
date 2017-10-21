    protected String guessMimeType(HttpServletRequest http_request, HttpServletResponse http_response)
    {
        String content_type = http_response.getContentType();
        LOG.debug("Content Type is: {}",content_type);

        String mime_type = "";
        if (content_type != null)
        {
            mime_type = MimeTypes.getContentTypeWithoutCharset(content_type);
            LOG.debug("Mime Type is: {}",mime_type);
        }
        else
        {
            String request_url = http_request.getPathInfo();
            mime_type = MimeTypes.getDefaultMimeByExtension(request_url);

            if (mime_type == null)
            {
                mime_type = "";
            }

            LOG.debug("Guessed mime type is {}",mime_type);
        }

        return mime_type;
    }
