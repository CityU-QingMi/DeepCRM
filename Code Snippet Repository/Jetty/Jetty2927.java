    private void extractContentParameters()
    {
        String contentType = getContentType();
        if (contentType == null || contentType.isEmpty())
            _contentParameters=NO_PARAMS;
        else
        {
            _contentParameters=new MultiMap<>();
            contentType = HttpFields.valueParameters(contentType, null);
            int contentLength = getContentLength();
            if (contentLength != 0)
            {
                if (MimeTypes.Type.FORM_ENCODED.is(contentType) && _inputState == __NONE &&
                    _channel.getHttpConfiguration().isFormEncodedMethod(getMethod()))
                {
                    extractFormParameters(_contentParameters);
                }
                else if (contentType.startsWith("multipart/form-data") &&
                        getAttribute(__MULTIPART_CONFIG_ELEMENT) != null &&
                        _multiPartInputStream == null)
                {
                    extractMultipartParameters(_contentParameters);
                }
            }
        }

    }
