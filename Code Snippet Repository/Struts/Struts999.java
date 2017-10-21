    public Source resolve(String href, String base) throws TransformerException {
        log.debug("ServletURIResolver resolve(): href={}, base={}", href, base);
        if (href.startsWith(PROTOCOL)) {
            String res = href.substring(PROTOCOL.length());
            log.debug("Resolving resource <{}>", res);

            InputStream is = sc.getResourceAsStream(res);

            if (is == null) {
                throw new TransformerException(
                        "Resource " + res + " not found in resources.");
            }

            return new StreamSource(is);
        }

        throw new TransformerException(
                "Cannot handle protocol of resource " + href);
    }
