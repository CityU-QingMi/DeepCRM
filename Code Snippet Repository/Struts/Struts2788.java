    private void checkScriptingBody(Node.ScriptingElement scriptingElem)
        throws SAXException {
        Node.Nodes body = scriptingElem.getBody();
        if (body != null) {
            int size = body.size();
            for (int i = 0; i < size; i++) {
                Node n = body.getNode(i);
                if (!(n instanceof Node.TemplateText)) {
                    String elemType = SCRIPTLET_ACTION;
                    if (scriptingElem instanceof Node.Declaration)
                        elemType = DECLARATION_ACTION;
                    if (scriptingElem instanceof Node.Expression)
                        elemType = EXPRESSION_ACTION;
                    String msg =
                        Localizer.getMessage(
                            "jsp.error.parse.xml.scripting.invalid.body",
                            elemType);
                    throw new SAXException(msg);
                }
            }
        }
    }
