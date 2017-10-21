    private void extractQueryParameters()
    {
        MetaData.Request metadata = _metaData;
        if (metadata==null || metadata.getURI() == null || !metadata.getURI().hasQuery())
            _queryParameters=NO_PARAMS;
        else
        {
            _queryParameters = new MultiMap<>();
            if (_queryEncoding == null)
                metadata.getURI().decodeQueryTo(_queryParameters);
            else
            {
                try
                {
                    metadata.getURI().decodeQueryTo(_queryParameters, _queryEncoding);
                }
                catch (UnsupportedEncodingException e)
                {
                    if (LOG.isDebugEnabled())
                        LOG.warn(e);
                    else
                        LOG.warn(e.toString());
                }
            }
        }
    }
