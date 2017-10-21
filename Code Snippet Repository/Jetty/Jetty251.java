        private void invalidate()
        {
            if (set.isEmpty())
            {
                encodingField = null;
            }
            else
            {
                StringBuilder value = new StringBuilder();
                for (Iterator<ContentDecoder.Factory> iterator = set.iterator(); iterator.hasNext();)
                {
                    ContentDecoder.Factory decoderFactory = iterator.next();
                    value.append(decoderFactory.getEncoding());
                    if (iterator.hasNext())
                        value.append(",");
                }
                encodingField = new HttpField(HttpHeader.ACCEPT_ENCODING, value.toString());
            }
        }
