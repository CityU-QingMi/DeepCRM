    private TagFileInfo createTagFileInfo(TreeNode elem, String uri,
            URL jarFileUrl) throws JasperException {

        String name = null;
        String path = null;

        Iterator list = elem.findChildren();
        while (list.hasNext()) {
            TreeNode child = (TreeNode) list.next();
            String tname = child.getName();
            if ("name".equals(tname)) {
                name = child.getBody();
            } else if ("path".equals(tname)) {
                path = child.getBody();
            } else if ("example".equals(tname)) {
                // Ignore <example> element: Bugzilla 33538
            } else if ("tag-extension".equals(tname)) {
                // Ignore <tag-extension> element: Bugzilla 33538
            } else if ("icon".equals(tname) 
                    || "display-name".equals(tname) 
                    || "description".equals(tname)) {
                // Ignore these elements: Bugzilla 38015
            } else {
                if (log.isWarnEnabled()) {
                    log.warn(Localizer.getMessage(
                            "jsp.warning.unknown.element.in.tagfile", tname));
                }
            }
        }

        if (path.startsWith("/META-INF/tags")) {
            // Tag file packaged in JAR
            // See https://issues.apache.org/bugzilla/show_bug.cgi?id=46471
            // This needs to be removed once all the broken code that depends on
            // it has been removed
            ctxt.setTagFileJarUrl(path, jarFileUrl);
        } else if (!path.startsWith("/WEB-INF/tags")) {
            err.jspError("jsp.error.tagfile.illegalPath", path);
        }

        TagInfo tagInfo = TagFileProcessor.parseTagFileDirectives(
                parserController, name, path, jarFileUrl, this);
        return new TagFileInfo(name, path, tagInfo);
    }
