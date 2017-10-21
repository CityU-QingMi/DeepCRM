    private void writeValue(String name, Object value, String expr) {
        Class clazz = value.getClass();
        if (clazz.isPrimitive() || Number.class.isAssignableFrom(clazz) ||
            clazz.equals(String.class) || Boolean.class.equals(clazz)) {
            prettyWriter.setValue(String.valueOf(value));
        } else {
            prettyWriter.startNode("a");
            String path = expr.replaceAll("#", "%23") + "[\"" +
                name.replaceAll("#", "%23") + "\"]";
            prettyWriter.addAttribute("onclick", "expand(this, '" + path + "')");
            prettyWriter.addAttribute("href", "javascript://nop/");
            prettyWriter.setValue("Expand");
            prettyWriter.endNode();
        }
    }
