    public void evaluateExtraParams() {
        super.evaluateExtraParams();

        String submitType = BUTTONTYPE_INPUT;
        if (type != null && (BUTTONTYPE_BUTTON.equalsIgnoreCase(type) || (supportsImageType() && BUTTONTYPE_IMAGE.equalsIgnoreCase(type))))
        {
            submitType = type;
        }

        //super.evaluateParams();

        addParameter("type", submitType);

        if (!BUTTONTYPE_INPUT.equals(submitType) && (label == null)) {
            addParameter("label", getParameters().get("nameValue"));
        }

        if (action != null || method != null) {
            String name;

            if (action != null) {
                ActionMapping mapping = new ActionMapping();
                mapping.setName(findString(action));
                if (method != null) {
                    mapping.setMethod(findString(method));
                }
                mapping.setExtension("");
                name = "action:" + actionMapper.getUriFromActionMapping(mapping);
            } else {
                name = "method:" + findString(method);
            }

            addParameter("name", name);
        }

    }
