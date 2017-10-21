        private String comparePageEncodings(String thePageDirEnc,
                Node.PageDirective pageDir) throws JasperException {

            Node.Root root = pageDir.getRoot();
            String configEnc = root.getJspConfigPageEncoding();
            String pageDirEnc = thePageDirEnc.toUpperCase();

/**/
/**/
/**/
/**/
/**/
/**/
            if (configEnc != null) {
                configEnc = configEnc.toUpperCase();
                if (!pageDirEnc.equals(configEnc)
                        && (!pageDirEnc.startsWith("UTF-16") || !configEnc
                                .startsWith("UTF-16"))) {
                    err.jspError(pageDir,
                            "jsp.error.config_pagedir_encoding_mismatch",
                            configEnc, pageDirEnc);
                } else {
                    return configEnc;
                }
            }

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
                } else {
                    return pageEnc;
                }
            }
            
            return pageDirEnc;
        }
