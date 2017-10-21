    protected Charset readCharset() {
        Charset charset = null;
        if (charSet != null) {
            if (Charset.isSupported(charSet)) {
                charset = Charset.forName(charSet);
            } else {
                LOG.warn("charset [{}] is not recognized", charset);
                charset = null;
            }
        }
        return charset;
    }
