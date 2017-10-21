    private void addInclude(Node parent, List files) throws SAXException {
        if (files != null) {
            Iterator iter = files.iterator();
            while (iter.hasNext()) {
                String file = (String)iter.next();
                AttributesImpl attrs = new AttributesImpl();
                attrs.addAttribute("", "file", "file", "CDATA", file);

                // Create a dummy Include directive node
                    Node includeDir =
                        new Node.IncludeDirective(attrs, null, // XXX
    parent);
                processIncludeDirective(file, includeDir);
            }
        }
    }
