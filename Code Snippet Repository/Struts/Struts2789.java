    private void processIncludeDirective(String fname, Node parent)
        throws SAXException {

        if (fname == null) {
            return;
        }

        try {
            parserController.parse(fname, parent, null);
        } catch (FileNotFoundException fnfe) {
            throw new SAXParseException(
                Localizer.getMessage("jsp.error.file.not.found", fname),
                locator,
                fnfe);
        } catch (Exception e) {
            throw new SAXException(e);
        }
    }
