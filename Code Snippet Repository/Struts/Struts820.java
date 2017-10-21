        public Object evaluate(String parsedValue) {
            if (encode) {
                if (parsedValue != null) {
                    try {
                        return URLEncoder.encode(parsedValue, DEFAULT_URL_ENCODING);
                    }
                    catch(UnsupportedEncodingException e) {
                        LOG.warn("error while trying to encode [{}]", parsedValue, e);
                    }
                }
            }
            return parsedValue;
        }
