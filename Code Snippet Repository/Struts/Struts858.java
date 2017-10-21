    @Override
    public void monitorFile(URL fileUrl) {
        if (isJBossUrl(fileUrl)) {
            String fileName = fileUrl.toString();
            LOG.debug("Creating revision for URL: {}", fileName);
            URL normalizedUrl = normalizeToFileProtocol(fileUrl);
            LOG.debug("Normalized URL for [{}] is [{}]", fileName, normalizedUrl);
            Revision revision;
            if ("file".equals(normalizedUrl.getProtocol())) {
                revision = FileRevision.build(normalizedUrl);
            } else if ("jar".equals(normalizedUrl.getProtocol())) {
                revision = JarEntryRevision.build(normalizedUrl);
            } else {
                revision = Revision.build(normalizedUrl);
            }
            files.put(fileName, revision);
        } else {
            super.monitorFile(fileUrl);
        }
    }
