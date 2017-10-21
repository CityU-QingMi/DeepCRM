        @Override
        public void setCharacterEncoding(String enc)
            throws UnsupportedEncodingException
        {
            try
            {
                _encoding=Charset.forName(enc);
            }
            catch (UnsupportedCharsetException e)
            {
                throw new UnsupportedEncodingException(e.getMessage());
            }
        }
