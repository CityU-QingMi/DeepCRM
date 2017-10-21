    protected URL getJBossPhysicalUrl(URL url) throws IOException {
        Object content = url.openConnection().getContent();
        String classContent = content.getClass().toString();
        LOG.debug("Reading physical URL for [{}]", url);
        if (classContent.startsWith("class org.jboss.vfs.VirtualFile")) { // JBoss 7 and probably 6
            File physicalFile = readJBossPhysicalFile(content);
            return physicalFile.toURI().toURL();
        } else if (classContent.startsWith("class org.jboss.virtual.VirtualFile")) { // JBoss 5
            return readJBoss5Url(content);
        }
        return url;
    }
