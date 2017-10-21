    private void writeProperty(String name, Object value, String expr) {
        prettyWriter.startNode("tr");

        //name cell
        prettyWriter.startNode("td");
        prettyWriter.addAttribute("class", "nameColumn");
        prettyWriter.setValue(name);
        prettyWriter.endNode();

        //value cell
        prettyWriter.startNode("td");
        if (value != null) {
            //if is is an empty collection or array, don't write a link
            if (isEmptyCollection(value) || isEmptyMap(value) || (value.getClass()
                    .isArray() && ((Object[]) value).length == 0)) {
                prettyWriter.addAttribute("class", "emptyCollection");
                prettyWriter.setValue("empty");
            } else {
                prettyWriter.addAttribute("class", "valueColumn");
                writeValue(name, value, expr);
            }
        } else {
            prettyWriter.addAttribute("class", "nullValue");
            prettyWriter.setValue("null");
        }
        prettyWriter.endNode();

        //type cell
        prettyWriter.startNode("td");
        if (value != null) {
            prettyWriter.addAttribute("class", "typeColumn");
            Class clazz = value.getClass();
            prettyWriter.setValue(clazz.getName());
        } else {
            prettyWriter.addAttribute("class", "nullValue");
            prettyWriter.setValue("unknown");
        }
        prettyWriter.endNode();

        //close tr
        prettyWriter.endNode();
    }
