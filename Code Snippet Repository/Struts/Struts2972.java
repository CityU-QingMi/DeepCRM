    private void init(ErrorDispatcher err) throws JasperException {
	if (initialized)
	    return;

	InputStream is = ctxt.getResourceAsStream(TAG_PLUGINS_XML);
	if (is == null)
	    return;

	TreeNode root = (new ParserUtils()).parseXMLDocument(TAG_PLUGINS_XML,
							     is);
	if (root == null) {
	    return;
	}

	if (!TAG_PLUGINS_ROOT_ELEM.equals(root.getName())) {
	    err.jspError("jsp.error.plugin.wrongRootElement", TAG_PLUGINS_XML,
			 TAG_PLUGINS_ROOT_ELEM);
	}

	tagPlugins = new HashMap();
	Iterator pluginList = root.findChildren("tag-plugin");
	while (pluginList.hasNext()) {
	    TreeNode pluginNode = (TreeNode) pluginList.next();
            TreeNode tagClassNode = pluginNode.findChild("tag-class");
	    if (tagClassNode == null) {
		// Error
		return;
	    }
	    String tagClass = tagClassNode.getBody().trim();
	    TreeNode pluginClassNode = pluginNode.findChild("plugin-class");
	    if (pluginClassNode == null) {
		// Error
		return;
	    }

	    String pluginClassStr = pluginClassNode.getBody();
	    TagPlugin tagPlugin = null;
	    try {
		Class pluginClass = Class.forName(pluginClassStr);
		tagPlugin = (TagPlugin) pluginClass.newInstance();
	    } catch (Exception e) {
		throw new JasperException(e);
	    }
	    if (tagPlugin == null) {
		return;
	    }
	    tagPlugins.put(tagClass, tagPlugin);
	}
	initialized = true;
    }
