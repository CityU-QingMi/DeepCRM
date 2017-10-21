    private String getRealLabel() {
        switch (type) {
            case TYPE_ACTION:
                return "action" + label;
            case TYPE_FORM:
                return "form" + label;
            case TYPE_HREF:
                return "href" + label;
            case TYPE_REDIRECT:
                return "redirect: " + label;
            case TYPE_RESULT:
                return label;
        }

        return "";
    }
