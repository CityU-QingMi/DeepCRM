    @Override
    public void setup() {
        final Iterator<Map.Entry<String, JsonNode>> iter = root.fields();
        final List<Node> children = rootNode.getChildren();
        while (iter.hasNext()) {
            final Map.Entry<String, JsonNode> entry = iter.next();
            final JsonNode n = entry.getValue();
            if (n.isObject()) {
                LOGGER.debug("Processing node for object {}", entry.getKey());
                children.add(constructNode(entry.getKey(), rootNode, n));
            } else if (n.isArray()) {
                LOGGER.error("Arrays are not supported at the root configuration.");
            }
        }
        LOGGER.debug("Completed parsing configuration");
        if (status.size() > 0) {
            for (final Status s : status) {
                LOGGER.error("Error processing element {}: {}", s.name, s.errorType);
            }
        }
    }
