        private String generateAliasMap(Node.CustomTag n, String tagHandlerVar)
                throws JasperException {

            TagVariableInfo[] tagVars = n.getTagVariableInfos();
            String aliasMapVar = null;

            boolean aliasSeen = false;
            for (int i = 0; i < tagVars.length; i++) {

                String nameFrom = tagVars[i].getNameFromAttribute();
                if (nameFrom != null) {
                    String aliasedName = n.getAttributeValue(nameFrom);
                    if (aliasedName == null)
                        continue;

                    if (!aliasSeen) {
                        out.printin("java.util.HashMap ");
                        aliasMapVar = tagHandlerVar + "_aliasMap";
                        out.print(aliasMapVar);
                        out.println(" = new java.util.HashMap();");
                        aliasSeen = true;
                    }
                    out.printin(aliasMapVar);
                    out.print(".put(");
                    out.print(quote(tagVars[i].getNameGiven()));
                    out.print(", ");
                    out.print(quote(aliasedName));
                    out.println(");");
                }
            }
            return aliasMapVar;
        }
