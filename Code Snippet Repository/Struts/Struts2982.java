        private void compareTagEncodings(String thePageDirEnc,
                Node.TagDirective pageDir) throws JasperException {

            Node.Root root = pageDir.getRoot();
            String pageDirEnc = thePageDirEnc.toUpperCase();
/**/
/**/
/**/
/**/
/**/
/**/
/**/
            if ((root.isXmlSyntax() && root.isEncodingSpecifiedInProlog()) || root.isBomPresent()) {
                String pageEnc = root.getPageEncoding().toUpperCase();
                if (!pageDirEnc.equals(pageEnc)
                        && (!pageDirEnc.startsWith("UTF-16") || !pageEnc
                                .startsWith("UTF-16"))) {
                    err.jspError(pageDir,
                            "jsp.error.prolog_pagedir_encoding_mismatch",
                            pageEnc, pageDirEnc);
                }
            }
        }
