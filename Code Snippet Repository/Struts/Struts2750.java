        private void declareScriptingVars(Node.CustomTag n, int scope) {

            Vector vec = n.getScriptingVars(scope);
            if (vec != null) {
                for (int i = 0; i < vec.size(); i++) {
                    Object elem = vec.elementAt(i);
                    if (elem instanceof VariableInfo) {
                        VariableInfo varInfo = (VariableInfo) elem;
                        if (varInfo.getDeclare()) {
                            out.printin(varInfo.getClassName());
                            out.print(" ");
                            out.print(varInfo.getVarName());
                            out.println(" = null;");
                        }
                    } else {
                        TagVariableInfo tagVarInfo = (TagVariableInfo) elem;
                        if (tagVarInfo.getDeclare()) {
                            String varName = tagVarInfo.getNameGiven();
                            if (varName == null) {
                                varName = n.getTagData().getAttributeString(
                                        tagVarInfo.getNameFromAttribute());
                            } else if (tagVarInfo.getNameFromAttribute() != null) {
                                // alias
                                continue;
                            }
                            out.printin(tagVarInfo.getClassName());
                            out.print(" ");
                            out.print(varName);
                            out.println(" = null;");
                        }
                    }
                }
            }
        }
