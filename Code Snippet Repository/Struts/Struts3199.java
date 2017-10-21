    private Boolean isChecked(Map<String, Object> params, String itemKeyStr) {
        Boolean checked = false;
        if (itemKeyStr != null) {

            // NameValue are the values that is provided by the name property
            // in the action
            Object nameValue = params.get("nameValue");

            if (nameValue != null) {

                Iterator itt = MakeIterator.convert(nameValue);
                while (itt.hasNext()) {

                    String value = itt.next().toString();
                    if (checked = value.equalsIgnoreCase(itemKeyStr)) {
                        break;
                    }

                }

            }
        }
        return checked;
    }
