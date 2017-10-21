        public CustomTag(String qName, String prefix, String localName,
                String uri, Attributes attrs, Attributes nonTaglibXmlnsAttrs,
                Attributes taglibAttrs, Mark start, Node parent,
                TagInfo tagInfo, Class tagHandlerClass) {
            super(qName, localName, attrs, nonTaglibXmlnsAttrs, taglibAttrs,
                    start, parent);

            this.uri = uri;
            this.prefix = prefix;
            this.tagInfo = tagInfo;
            this.tagHandlerClass = tagHandlerClass;
            this.customNestingLevel = makeCustomNestingLevel();
            this.childInfo = new ChildInfo();

            this.implementsIterationTag = IterationTag.class
                    .isAssignableFrom(tagHandlerClass);
            this.implementsBodyTag = BodyTag.class
                    .isAssignableFrom(tagHandlerClass);
            this.implementsTryCatchFinally = TryCatchFinally.class
                    .isAssignableFrom(tagHandlerClass);
            this.implementsSimpleTag = SimpleTag.class
                    .isAssignableFrom(tagHandlerClass);
            this.implementsDynamicAttributes = DynamicAttributes.class
                    .isAssignableFrom(tagHandlerClass);
            this.implementsJspIdConsumer = JspIdConsumer.class
                    .isAssignableFrom(tagHandlerClass);
        }
