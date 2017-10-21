        private void checkUniqueName(String name, String type, Node n,
                TagAttributeInfo attr) throws JasperException {

            HashMap table = (type == VAR_NAME_FROM) ? nameFromTable : nameTable;
            NameEntry nameEntry = (NameEntry) table.get(name);
            if (nameEntry != null) {
                if (type != TAG_DYNAMIC || nameEntry.getType() != TAG_DYNAMIC) {
                    int line = nameEntry.getNode().getStart().getLineNumber();
                    err.jspError(n, "jsp.error.tagfile.nameNotUnique", type,
                            nameEntry.getType(), Integer.toString(line));
                }
            } else {
                table.put(name, new NameEntry(type, n, attr));
            }
        }
