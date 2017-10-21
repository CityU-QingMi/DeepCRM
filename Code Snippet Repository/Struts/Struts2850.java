        public CustomTag(String qName, String prefix, String localName,
                String uri, Attributes attrs, Attributes nonTaglibXmlnsAttrs,
                Attributes taglibAttrs, Mark start, Node parent,
                TagFileInfo tagFileInfo) {

            super(qName, localName, attrs, nonTaglibXmlnsAttrs, taglibAttrs,
                    start, parent);

            this.uri = uri;
            this.prefix = prefix;
            this.tagFileInfo = tagFileInfo;
            this.tagInfo = tagFileInfo.getTagInfo();
            this.customNestingLevel = makeCustomNestingLevel();
            this.childInfo = new ChildInfo();

            this.implementsIterationTag = false;
            this.implementsBodyTag = false;
            this.implementsTryCatchFinally = false;
            this.implementsSimpleTag = true;
            this.implementsJspIdConsumer = false;
            this.implementsDynamicAttributes = tagInfo.hasDynamicAttributes();
        }
