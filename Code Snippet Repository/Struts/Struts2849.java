    public Node(String qName, String localName, Attributes attrs,
            Attributes nonTaglibXmlnsAttrs, Attributes taglibAttrs, Mark start,
            Node parent) {
        this.qName = qName;
        this.localName = localName;
        this.attrs = attrs;
        this.nonTaglibXmlnsAttrs = nonTaglibXmlnsAttrs;
        this.taglibAttrs = taglibAttrs;
        this.startMark = start;
        this.isDummy = (start == null);
        addToParent(parent);
    }
