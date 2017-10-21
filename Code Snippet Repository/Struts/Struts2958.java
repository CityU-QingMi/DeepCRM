        void postCheck() throws JasperException {
            // Check that var.name-from-attributes has valid values.
            Iterator iter = nameFromTable.keySet().iterator();
            while (iter.hasNext()) {
                String nameFrom = (String) iter.next();
                NameEntry nameEntry = (NameEntry) nameTable.get(nameFrom);
                NameEntry nameFromEntry = (NameEntry) nameFromTable
                        .get(nameFrom);
                Node nameFromNode = nameFromEntry.getNode();
                if (nameEntry == null) {
                    err.jspError(nameFromNode,
                            "jsp.error.tagfile.nameFrom.noAttribute", nameFrom);
                } else {
                    Node node = nameEntry.getNode();
                    TagAttributeInfo tagAttr = nameEntry.getTagAttributeInfo();
                    if (!"java.lang.String".equals(tagAttr.getTypeName())
                            || !tagAttr.isRequired()
                            || tagAttr.canBeRequestTime()) {
                        err.jspError(nameFromNode,
                                "jsp.error.tagfile.nameFrom.badAttribute",
                                nameFrom, Integer.toString(node.getStart()
                                        .getLineNumber()));
                    }
                }
            }
        }
