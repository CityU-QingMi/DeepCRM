        public void visit(Node.ELExpression n) throws JasperException {
            n.setBeginJavaLine(out.getJavaLine());
            if (!pageInfo.isELIgnored() && (n.getEL() != null)) {
                out.printil("out.write("
                        + JspUtil.interpreterCall(this.isTagFile, n.getType() + "{"
                                + new String(n.getText()) + "}", String.class,
                                n.getEL().getMapName(), false) + ");");
            } else {
                out.printil("out.write("
                        + quote(n.getType() + "{" + new String(n.getText()) + "}") + ");");
            }
            n.setEndJavaLine(out.getJavaLine());
        }
