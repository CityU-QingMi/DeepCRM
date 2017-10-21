    public void monitorFile(URL fileUrl) {
        String fileName = fileUrl.toString();
        Revision revision;
        LOG.debug("Creating revision for URL: {}", fileName);
        if (isJarURL(fileUrl)) {
            revision = JarEntryRevision.build(fileUrl, this);
        } else {
            revision = FileRevision.build(fileUrl);
        }
        if (revision == null) {
            files.put(fileName, Revision.build(fileUrl));
        } else {
            files.put(fileName, revision);
        }
    }
