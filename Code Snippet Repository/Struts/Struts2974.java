        public void visit(Node.TemplateText n) throws JasperException {
            if ((options.getTrimSpaces() || pageInfo.isTrimDirectiveWhitespaces()) 
                    && n.isAllSpace()) {
                n.setText(emptyText);
                return;
            }

            if (textNodeCount++ == 0) {
                firstTextNode = n;
                textBuffer = new StringBuffer(n.getText());
            } else {
                // Append text to text buffer
                textBuffer.append(n.getText());
                n.setText(emptyText);
            }
        }
