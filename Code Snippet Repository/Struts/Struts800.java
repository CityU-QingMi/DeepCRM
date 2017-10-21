    @SuppressWarnings("")
    public void write(ReflectionProvider reflectionProvider, Object root, String expr) throws IntrospectionException,
        ReflectionException {
        prettyWriter.startNode("table");
        prettyWriter.addAttribute("class", "debugTable");

        if (root instanceof Map) {
            for (Object next : ((Map) root).entrySet()) {
                Map.Entry property = (Map.Entry) next;
                String key = property.getKey().toString();
                Object value = property.getValue();
                writeProperty(key, value, expr);
            }
        } else if (root instanceof List) {
            List list = (List) root;
            for (int i = 0; i < list.size(); i++) {
                Object element = list.get(i);
                writeProperty(String.valueOf(i), element, expr);
            }
        } else if (root instanceof Set) {
            Set set = (Set) root;
            for (Object next : set) {
                writeProperty("", next, expr);
            }
        } else if (root.getClass().isArray()) {
            Object[] objects = (Object[]) root;
            for (int i = 0; i < objects.length; i++) {
                writeProperty(String.valueOf(i), objects[i], expr);
            }
        } else {
            //print properties
            Map<String, Object> properties = reflectionProvider.getBeanMap(root);
            for (Map.Entry<String, Object> property : properties.entrySet()) {
                String name = property.getKey();
                Object value = property.getValue();

                if ("class".equals(name)) {
                    continue;
                }

                writeProperty(name, value, expr);
            }
        }

        prettyWriter.endNode();
    }
