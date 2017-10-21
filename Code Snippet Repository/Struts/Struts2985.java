        public void visit(Node.CustomTag n) throws JasperException {
            TagInfo tagInfo = n.getTagInfo();
            if (tagInfo == null) {
                err.jspError(n, "jsp.error.missing.tagInfo", n.getQName());
            }

            ValidationMessage[] errors = tagInfo.validate(n.getTagData());
            if (errors != null && errors.length != 0) {
                StringBuffer errMsg = new StringBuffer();
                errMsg.append("<h3>");
                errMsg.append(Localizer.getMessage(
                        "jsp.error.tei.invalid.attributes", n.getQName()));
                errMsg.append("</h3>");
                for (int i = 0; i < errors.length; i++) {
                    errMsg.append("<p>");
                    if (errors[i].getId() != null) {
                        errMsg.append(errors[i].getId());
                        errMsg.append(": ");
                    }
                    errMsg.append(errors[i].getMessage());
                    errMsg.append("</p>");
                }

                err.jspError(n, errMsg.toString());
            }

            visitBody(n);
        }
