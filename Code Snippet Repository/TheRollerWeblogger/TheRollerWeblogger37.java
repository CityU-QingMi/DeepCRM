    public Module parse(Element element, Locale locale) {
        boolean foundSomething = false;
        ContentModule fm = new ContentModuleImpl();

        Element e = element.getChild("encoded", getContentNamespace());
        if (e != null) {
            foundSomething = true;
            fm.setEncoded(e.getText());
        }
        return (foundSomething) ? fm : null;
    }
