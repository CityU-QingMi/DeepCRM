    public SiteGraphNode findNode(String location, SiteGraphNode ref) {
        if (location.startsWith("/")) {
            location = location.substring(1);
        } else {
            // not absolute, so use the reference node
            String prefix;
            if (ref.getParent() != null) {
                prefix = ref.getParent().getPrefix();
                location = prefix + "_" + location;
            }
        }

        location = location.replaceAll("[\\./\\-\\$\\{\\}]", "_");

        return nodeMap.get(location);
    }
