    private void scanXMLDecl() throws IOException, JasperException {

        if (skipString("<?xml")) {
            fMarkupDepth++;
            // NOTE: special case where document starts with a PI
            //       whose name starts with "xml" (e.g. "xmlfoo")
            if (XMLChar.isName(peekChar())) {
                fStringBuffer.clear();
                fStringBuffer.append("xml");
                while (XMLChar.isName(peekChar())) {
                    fStringBuffer.append((char) scanChar());
                }
                String target = fSymbolTable.addSymbol(fStringBuffer.ch,
                        fStringBuffer.offset,
                        fStringBuffer.length);
                scanPIData(target, fString);
            }

            // standard XML declaration
            else {
                scanXMLDeclOrTextDecl(false);
            }
        }
    }
