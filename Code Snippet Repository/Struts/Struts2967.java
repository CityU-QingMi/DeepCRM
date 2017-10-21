    private TagLibraryValidator createValidator(TreeNode elem)
            throws JasperException {

        String validatorClass = null;
        Map initParams = new Hashtable();

        Iterator list = elem.findChildren();
        while (list.hasNext()) {
            TreeNode element = (TreeNode) list.next();
            String tname = element.getName();
            if ("validator-class".equals(tname))
                validatorClass = element.getBody();
            else if ("init-param".equals(tname)) {
                String[] initParam = createInitParam(element);
                initParams.put(initParam[0], initParam[1]);
            } else if ("description".equals(tname) || // Ignored elements
            false) {
            } else {
                if (log.isWarnEnabled()) {
                    log.warn(Localizer.getMessage(
                            "jsp.warning.unknown.element.in.validator", tname));
                }
            }
        }

        TagLibraryValidator tlv = null;
        if (validatorClass != null && !validatorClass.equals("")) {
            try {
                Class tlvClass = ctxt.getClassLoader()
                        .loadClass(validatorClass);
                tlv = (TagLibraryValidator) tlvClass.newInstance();
            } catch (Exception e) {
                err.jspError("jsp.error.tlvclass.instantiation",
                        validatorClass, e);
            }
        }
        if (tlv != null) {
            tlv.setInitParameters(initParams);
        }
        return tlv;
    }
