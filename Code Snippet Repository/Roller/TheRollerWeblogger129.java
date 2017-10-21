    public void importBookmarks(
            Weblog website, String folderName, String opml)
            throws WebloggerException {

        try {
            // Build JDOC document OPML string
            SAXBuilder builder = new SAXBuilder();
            StringReader reader = new StringReader( opml );
            Document doc = builder.build( reader );

            WeblogBookmarkFolder newFolder = getFolder(website, folderName);
            if (newFolder == null) {
                newFolder = new WeblogBookmarkFolder(
                        folderName, website);
                this.strategy.store(newFolder);
            }

            // Iterate through children of OPML body, importing each
            Element body = doc.getRootElement().getChild("body");
            for (Object elem : body.getChildren()) {
                importOpmlElement((Element) elem, newFolder );
            }
        } catch (Exception ex) {
            throw new WebloggerException(ex);
        }
    }
