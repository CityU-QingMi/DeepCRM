        public NamedAttribute(String qName, Attributes attrs,
                Attributes nonTaglibXmlnsAttrs, Attributes taglibAttrs,
                Mark start, Node parent) {

            super(qName, ATTRIBUTE_ACTION, attrs, nonTaglibXmlnsAttrs,
                    taglibAttrs, start, parent);
            if ("false".equals(this.getAttributeValue("trim"))) {
                // (if null or true, leave default of true)
                trim = false;
            }
            childInfo = new ChildInfo();
            name = this.getAttributeValue("name");
            if (name != null) {
                // Mandatary attribute "name" will be checked in Validator
                localName = name;
                int index = name.indexOf(':');
                if (index != -1) {
                    prefix = name.substring(0, index);
                    localName = name.substring(index + 1);
                }
            }
        }
