    protected boolean isPathUrl(String url) {
        try {
            String rawUrl = url;
            if (url.contains("?")) {
                rawUrl = url.substring(0, url.indexOf("?"));
            }
            URI uri = URI.create(rawUrl.replaceAll(" ", "%20"));
            if (uri.isAbsolute()) {
                URL validUrl = uri.toURL();
                LOG.debug("[{}] is full url, not a path", url);
                return validUrl.getProtocol() == null;
            } else {
                LOG.debug("[{}] isn't absolute URI, assuming it's a path", url);
                return true;
            }
        } catch (IllegalArgumentException e) {
            LOG.debug("[{}] isn't a valid URL, assuming it's a path", url, e);
            return true;
        } catch (MalformedURLException e) {
            LOG.debug("[{}] isn't a valid URL, assuming it's a path", url, e);
            return true;
        }
    }
