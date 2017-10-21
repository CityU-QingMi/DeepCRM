    @Override
    public URL normalizeToFileProtocol(URL url) {
        if (isJBossUrl(url))                {
            try {
                return getJBossPhysicalUrl(url);
            } catch (IOException e) {
                if (LOG.isErrorEnabled()) {
                    LOG.error(e.getMessage(), e);
                }
                return null;
            }
        } else {
            return super.normalizeToFileProtocol(url);
        }
    }
